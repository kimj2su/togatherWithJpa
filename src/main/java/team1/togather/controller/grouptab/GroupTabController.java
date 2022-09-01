package team1.togather.controller.grouptab;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team1.togather.config.file.FileStore;
import team1.togather.domain.groupTab.UploadFile;
import team1.togather.dto.request.GroupTabRequestDto;
import team1.togather.dto.response.GroupTabResponseDto;
import team1.togather.dto.response.GroupTabWithMembersResponseDto;
import team1.togather.dto.response.MemberInGroupTabResponseDto;
import team1.togather.security.auth.PrincipalDetails;
import team1.togather.service.PaginationService;
import team1.togather.service.grouptab.GroupTabService;
import team1.togather.service.grouptab.MemberInGroupTabService;
import team1.togather.service.member.CategoryService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/groupTabs")
public class GroupTabController {

    private final GroupTabService groupTabService;
    private final MemberInGroupTabService memberInGroupTabService;
    private final CategoryService categoryService;
    private final FileStore fileStore;
    private final PaginationService paginationService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("groupTab", new GroupTabRequestDto());
        model.addAttribute("intOut",categoryService.getIntOut());
        return "groupTabs/createGroupTabForm";
    }

    @PostMapping("/new")
    public String saveGroupTab(@Valid @ModelAttribute("groupTab") GroupTabRequestDto groupTabRequestDto, BindingResult bindingResult,
                               @AuthenticationPrincipal PrincipalDetails principalDetails, RedirectAttributes redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "groupTabs/createGroupTabForm";
        }
        UploadFile uploadFile = fileStore.storeFile(groupTabRequestDto.getAttachFile()); // UUID에서 리턴받은 파일네임
        groupTabRequestDto.setUploadFile(uploadFile);

        Long groupTabId = groupTabService.saveGroupTab(groupTabRequestDto.toDto(principalDetails.toDto()));
        redirectAttributes.addAttribute("groupTabId", groupTabId);
        return "redirect:/groupTabs/{groupTabId}";
    }

    @GetMapping("/{groupTabId}")
    public String groupTab(@PathVariable Long groupTabId, ModelMap modelMap, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        GroupTabWithMembersResponseDto groupTab = GroupTabWithMembersResponseDto.from(groupTabService.getGroupTabWithMembers(groupTabId));
        MemberInGroupTabResponseDto checkMember = MemberInGroupTabResponseDto.from(memberInGroupTabService.searchMemberInGroupTab(groupTabId, principalDetails.getMember().getId()));

        modelMap.addAttribute("groupTab", groupTab);
        modelMap.addAttribute("membersNameList", groupTab.getMemberInGroupTabResponseDtos());
        modelMap.addAttribute("checkMember", checkMember);
        return "groupTabs/detail";
    }

    @GetMapping("/search-category")
    public String searchCategory(@PageableDefault(size = 6, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable, String searchValue, Model model) {
        System.out.println("searchValue = " + searchValue);
        Page<GroupTabResponseDto> groupTabs = groupTabService.searchGroupTabs(searchValue, pageable).map(GroupTabResponseDto::from);
        List<Integer> paginationBarNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), groupTabs.getTotalPages());
        model.addAttribute("groupTabs", groupTabs);
        model.addAttribute("paginationBarNumbers", paginationBarNumbers);
        return "groupTabs/search-grouptab";
    }

}

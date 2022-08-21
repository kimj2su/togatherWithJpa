package team1.togather.controller.grouptab;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import team1.togather.config.file.FileStore;
import team1.togather.domain.groupTab.UploadFile;
import team1.togather.dto.request.GroupTabRequestDto;
import team1.togather.dto.response.GroupTabWithMembersResponseDto;
import team1.togather.dto.response.MemberInGroupTabResponseDto;
import team1.togather.security.auth.PrincipalDetails;
import team1.togather.service.GroupTabService;
import team1.togather.service.MemberInGroupTabService;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/groupTabs")
public class GroupTabController {

    private final GroupTabService groupTabService;
    private final MemberInGroupTabService memberInGroupTabService;
    private final FileStore fileStore;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("groupTab", new GroupTabRequestDto());
        return "groupTabs/createGroupTabForm";
    }

    @PostMapping("/new")
    public String saveGroupTab(@Valid @ModelAttribute("groupTab") GroupTabRequestDto groupTabRequestDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException {
        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "groupTabs/createGroupTabForm";
        }
        UploadFile uploadFile = fileStore.storeFile(groupTabRequestDto.getAttachFile()); // UUID에서 리턴받은 파일네임
        groupTabRequestDto.setUploadFile(uploadFile);

        groupTabService.saveGroupTab(groupTabRequestDto.toDto(principalDetails.toDto()));
        return "redirect:/";
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

}

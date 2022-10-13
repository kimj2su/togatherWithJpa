package team1.togather.controller.grouptab;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team1.togather.config.file.FileStore;
import team1.togather.domain.groupTab.UploadFile;
import team1.togather.dto.GroupTabDto;
import team1.togather.dto.request.GroupTabRequestDto;
import team1.togather.dto.response.GroupTabResponseDto;
import team1.togather.dto.response.GroupTabWithMembersResponseDto;
import team1.togather.dto.response.MemberInGroupTabResponseDto;
import team1.togather.security.auth.PrincipalDetails;
import team1.togather.service.grouptab.GroupTabService;
import team1.togather.service.grouptab.MemberInGroupTabService;
import team1.togather.service.member.CategoryService;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/groupTabs")
public class GroupTabController {

    private final GroupTabService groupTabService;
    private final MemberInGroupTabService memberInGroupTabService;
    private final CategoryService categoryService;
    private final FileStore fileStore;

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

    @GetMapping("/{groupTabId}/form")
    public String modifyGroupForm(@PathVariable Long groupTabId, Model model) {
        GroupTabResponseDto groupTab = GroupTabResponseDto.from(groupTabService.getGroupTab(groupTabId));
        model.addAttribute("groupTab", groupTab);
        model.addAttribute("intOut",categoryService.getIntOut());
        return "groupTabs/modifyGroupTabForm";
    }

    @PostMapping("/{groupTabId}/form")
    public String modifyGroupForm(@PathVariable Long groupTabId, @Valid @ModelAttribute("groupTab") GroupTabRequestDto groupTabRequestDto, BindingResult bindingResult,
                               @AuthenticationPrincipal PrincipalDetails principalDetails, RedirectAttributes redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "groupTabs/modifyGroupTabForm";
        }
        if (groupTabRequestDto.getAttachFile() != null) {
            UploadFile uploadFile = fileStore.storeFile(groupTabRequestDto.getAttachFile()); // UUID에서 리턴받은 파일네임
            groupTabRequestDto.setUploadFile(uploadFile);
        }
        GroupTabDto dto = groupTabService.updateGroupTab(groupTabId, groupTabRequestDto.toDto(principalDetails.toDto()));
        System.out.println("dto.getModifiedAt() = " + dto.getModifiedAt());

        redirectAttributes.addAttribute("groupTabId", groupTabId);
        return "redirect:/groupTabs/{groupTabId}";
    }

    @GetMapping("/{groupTabId}")
    public String groupTab(@PathVariable Long groupTabId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        GroupTabWithMembersResponseDto groupTab = GroupTabWithMembersResponseDto.from(groupTabService.getGroupTabWithMembers(groupTabId));
        MemberInGroupTabResponseDto checkMember = MemberInGroupTabResponseDto.from(memberInGroupTabService.searchMemberInGroupTab(groupTabId, principalDetails.getMember().getId()));

        model.addAttribute("groupTab", groupTab);
        model.addAttribute("membersNameList", groupTab.getMemberInGroupTabResponseDtos());
        model.addAttribute("gatherings", groupTab.getGatheringsResponseDtos());
        model.addAttribute("checkMember", checkMember);
        model.addAttribute("chatRoomId", groupTab.getChatRoomDto().getId());
        return "groupTabs/detail";
    }

    @PostMapping("/{groupTabId}/delete")
    public String deleteGroupTab(@PathVariable Long groupTabId) {
        groupTabService.deleteGroupTab(groupTabId);
        return "redirect:/";
    }
}

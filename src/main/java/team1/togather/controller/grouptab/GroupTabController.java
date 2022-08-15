package team1.togather.controller.grouptab;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team1.togather.config.file.FileStore;
import team1.togather.domain.groupTab.UploadFile;
import team1.togather.domain.member.Member;
import team1.togather.dto.request.GroupTabRequestDto;
import team1.togather.dto.response.GroupTabResponseDto;
import team1.togather.security.auth.PrincipalDetails;
import team1.togather.service.GroupTabService;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/groupTabs")
public class GroupTabController {

    private final GroupTabService groupTabService;
    private final FileStore fileStore;

    @GetMapping("/{groupTabId}")
    public String groupTab(@PathVariable Long groupTabId, ModelMap modelMap) {
        GroupTabResponseDto groupTabResponseDto = GroupTabResponseDto.from(groupTabService.getGroupTab(groupTabId));

        modelMap.addAttribute("groupTabResponseDto", groupTabResponseDto);
        return "groupTabs/detail";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("groupTab", new GroupTabRequestDto());
        return "groupTabs/createGroupTabForm";
    }

    @PostMapping("/new")
    public String saveGroupTab(@ModelAttribute GroupTabRequestDto groupTabRequestDto, Authentication authentication, RedirectAttributes
            redirectAttributes) throws IOException {
        UploadFile attachFile = fileStore.storeFile(groupTabRequestDto.getAttachFile()); // UUID에서 리턴받은 파일네임
        groupTabRequestDto.setUploadFile(attachFile);
        Member member = (Member) authentication.getPrincipal();
        groupTabService.saveGroupTab(groupTabRequestDto.toDto(member));

        return "redirect:/";
    }

}

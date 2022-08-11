package team1.togather.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team1.togather.config.file.FileStore;
import team1.togather.domain.group.UploadFile;
import team1.togather.dto.request.GroupTabRequestDto;
import team1.togather.security.auth.PrincipalDetails;
import team1.togather.service.GroupTabService;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Controller
public class GroupTabController {

    private final GroupTabService groupTabService;
    private final FileStore fileStore;

    @GetMapping("/groupTabs/new")
    public String createForm(Model model) {
        model.addAttribute("groupTab", new GroupTabRequestDto());
        return "groupTabs/createGroupTabForm";
    }

    @PostMapping("/groupTabs/new")
    public String saveItem(@ModelAttribute GroupTabRequestDto groupTabRequestDto, @AuthenticationPrincipal PrincipalDetails principalDetails, RedirectAttributes
            redirectAttributes) throws IOException {
        UploadFile attachFile = fileStore.storeFile(groupTabRequestDto.getAttachFile()); // UUID에서 리턴받은 파일네임
        groupTabRequestDto.setUploadFile(attachFile);
        groupTabService.saveGroupTab(groupTabRequestDto.toDto(principalDetails.getMember().getId()));

        return "redirect:/";
    }

}

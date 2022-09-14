package team1.togather.controller.grouptab.ingrouptab;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team1.togather.security.auth.PrincipalDetails;
import team1.togather.service.grouptab.ChatService;

@RequestMapping("/chat")
@RequiredArgsConstructor
@Controller
public class ChatCRUDController {

    private final ChatService chatService;

    @GetMapping
    public String chat(Long chatRoomId, String groupName, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("chatRoomId = " + chatRoomId);
//        chatService.getChatRoom(groupTabId);
        model.addAttribute("chatRoomId", chatRoomId);
        model.addAttribute("groupName", groupName);
        model.addAttribute("userName", principalDetails.getMember().getUserId());
        return "groupTabs/chat";
    }

    @PostMapping("/new")
    public String createChat(Long groupTabId, String groupName, RedirectAttributes redirectAttributes) {
//        Long chatRoomId = chatService.createChat(groupTabId);
        redirectAttributes.addAttribute("groupTabId", groupTabId);
        redirectAttributes.addAttribute("groupName", groupName);
        return "redirect:/chat";
    }

}

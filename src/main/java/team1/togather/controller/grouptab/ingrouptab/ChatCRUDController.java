package team1.togather.controller.grouptab.ingrouptab;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team1.togather.dto.ChatRoomDto;
import team1.togather.security.auth.PrincipalDetails;
import team1.togather.service.chat.ChatService;

@RequestMapping("/chat")
@RequiredArgsConstructor
@Controller
public class ChatCRUDController {

    private final ChatService chatService;

    @GetMapping
    public String chat(Long chatRoomId, String groupName, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        ChatRoomDto chatRoomDto = chatService.getChatRoomDto(chatRoomId);

        model.addAttribute("chatRoomId", chatRoomDto.getId());
        model.addAttribute("groupName", groupName);
        model.addAttribute("userName", principalDetails.getMember().getUserId());
        model.addAttribute("chatMessage", chatRoomDto.getChatMessageDtos());
        return "groupTabs/chat";
    }

}

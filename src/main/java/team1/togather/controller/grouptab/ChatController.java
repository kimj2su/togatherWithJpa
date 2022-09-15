package team1.togather.controller.grouptab;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import team1.togather.dto.request.ChatRequestDto;
import team1.togather.service.grouptab.ChatMessageService;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat_sendMessage/{chatRoomId}")
    @SendTo("/topic/{chatRoomId}")
    public ChatRequestDto sendMessage(ChatRequestDto chatMessage) {
        chatMessageService.saveChatMessage(chatMessage.toDto());
        return chatMessage;
    }

    @MessageMapping("/chat_addUser/{chatRoomId}")
    @SendTo("/topic/{chatRoomId}")
    public ChatRequestDto addUser(ChatRequestDto chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("chatRoomId", chatMessage.getChatRoomId());
        return chatMessage;
    }
}

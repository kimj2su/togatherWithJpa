package team1.togather.controller.grouptab;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import team1.togather.dto.request.ChatRequestDto;

@Controller
public class ChatController {

    @MessageMapping("/chat_sendMessage/{chatRoomId}")
    @SendTo("/topic/{chatRoomId}")
    public ChatRequestDto sendMessage(ChatRequestDto chatMessage) {
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

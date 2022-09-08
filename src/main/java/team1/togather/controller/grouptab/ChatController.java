package team1.togather.controller.grouptab;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import team1.togather.dto.ChatDto;

@Controller
public class ChatController {

    @MessageMapping("/chat_sendMessage/{groupTabId}")
    @SendTo("/topic/{groupTabId}")
    public ChatDto sendMessage(ChatDto chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat_addUser/{groupTabId}")
    @SendTo("/topic/{groupTabId}")
    public ChatDto addUser(ChatDto chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("groupTabId", chatMessage.getGroupTabId());
        return chatMessage;
    }
}

package team1.togather.dto;

import lombok.Data;
import team1.togather.domain.groupTab.ingrouptab.ChatMessage;
import team1.togather.domain.groupTab.ingrouptab.ChatRoom;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChatMessageDto {

    private final Long id;

    private final String userName;

    private final String content;

    private final LocalDateTime sendAt;

    private final Long chatRoomId;

    public static ChatMessageDto of(String userName, String content, LocalDateTime sendAt, Long chatRoomId) {
        return new ChatMessageDto(null, userName, content, sendAt, chatRoomId);
    }
    public static ChatMessageDto of(Long id, String userName, String content, LocalDateTime sendAt, Long chatRoomId) {
        return new ChatMessageDto(id, userName, content, sendAt, chatRoomId);
    }

    public static ChatMessageDto from(ChatMessage entity) {
        return new ChatMessageDto(
                entity.getId(),
                entity.getUserName(),
                entity.getContent(),
                entity.getSendAt(),
                entity.getChatRoom().getId()
        );
    }
    public ChatMessage toEntity(ChatRoom chatRoom) {
        return ChatMessage.of(
                userName,
                content,
                chatRoom
        );
    }
}

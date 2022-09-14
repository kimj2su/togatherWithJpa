package team1.togather.dto;

import lombok.Data;
import team1.togather.domain.groupTab.GroupTab;

import team1.togather.domain.groupTab.ingrouptab.ChatRoom;


@Data
public class ChatRoomDto {

    private final Long id;

    public static ChatRoomDto of() {
        return new ChatRoomDto(null);
    }

    public static ChatRoomDto from(ChatRoom chatRoom) {
        return new ChatRoomDto(chatRoom.getId());
    }
    public ChatRoom toEntity() {
        return ChatRoom.of();
    }
    
}

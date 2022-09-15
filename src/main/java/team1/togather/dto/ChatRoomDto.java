package team1.togather.dto;

import lombok.Data;

import team1.togather.domain.groupTab.ingrouptab.ChatMessage;
import team1.togather.domain.groupTab.ingrouptab.ChatRoom;
import team1.togather.dto.request.ChatRequestDto;

import java.util.List;
import java.util.stream.Collectors;


@Data
public class ChatRoomDto {

    private final Long id;

    private final List<ChatMessageDto> chatMessageDtos;

    public static ChatRoomDto of() {
        return new ChatRoomDto(null, null);
    }
    public static ChatRoomDto of(List<ChatMessageDto> chatMessageDtos) {
        return new ChatRoomDto(null, chatMessageDtos);
    }
    public static ChatRoomDto of(Long chatRoomId, List<ChatMessageDto> chatMessageDtos) {
        return new ChatRoomDto(chatRoomId, chatMessageDtos);
    }

    public static ChatRoomDto from(ChatRoom entity) {
        return new ChatRoomDto(entity.getId(), entity.getChatMessages().stream().map(ChatMessageDto::from).collect(Collectors.toList()));
    }
    public ChatRoom toEntity() {
        return ChatRoom.of();
    }
    
}

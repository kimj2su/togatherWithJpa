package team1.togather.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team1.togather.domain.constant.MessageType;
import team1.togather.dto.ChatMessageDto;
import team1.togather.dto.GatheringDto;
import team1.togather.dto.MemberDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequestDto {

    private MessageType type;
    private String content;
    private String sender;
    private Long chatRoomId;
    public ChatMessageDto toDto() {
        return ChatMessageDto.of(
                sender,
                content,
                null,
                chatRoomId
        );
    }
}

package team1.togather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team1.togather.domain.constant.MessageType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {

    private MessageType type;
    private String content;
    private String sender;
    private Long groupTabId;
}

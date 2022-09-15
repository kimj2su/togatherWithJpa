package team1.togather.domain.groupTab.ingrouptab;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_message_id")
    private Long id;

    private String userName;

    private String content;

    private LocalDateTime sendAt;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    public ChatMessage(String userName, String content, ChatRoom chatRoom) {
        this.userName = userName;
        this.content = content;
        this.sendAt = LocalDateTime.now();
        this.chatRoom = chatRoom;
    }

    public static ChatMessage of(String userName, String content, ChatRoom chatRoom) {
        return new ChatMessage(userName, content, chatRoom);
    }
}

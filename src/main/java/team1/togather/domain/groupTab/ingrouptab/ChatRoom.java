package team1.togather.domain.groupTab.ingrouptab;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Objects;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;

    public ChatRoom(Long id) {
        this.id = id;
    }

    public static ChatRoom of() {
        return new ChatRoom(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatRoom)) return false;
        ChatRoom chatRoom = (ChatRoom) o;
        return getId() != null && getId().equals(chatRoom.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

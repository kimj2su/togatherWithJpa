package team1.togather.domain.groupTab.ingrouptab;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team1.togather.domain.groupTab.GroupTab;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "group_id")
    private GroupTab groupTab;

    public ChatRoom(GroupTab groupTab) {
        this.groupTab = groupTab;
    }

    public static ChatRoom of(GroupTab groupTab) {
        return new ChatRoom(groupTab);
    }

    public ChatRoom addGroupTab(GroupTab groupTab) {
        return ChatRoom.of(groupTab);
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

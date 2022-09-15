package team1.togather.repository.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import team1.togather.domain.groupTab.ingrouptab.ChatRoom;

public interface ChatRepository extends JpaRepository<ChatRoom, Long> {

}

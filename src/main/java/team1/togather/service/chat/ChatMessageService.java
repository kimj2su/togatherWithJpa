package team1.togather.service.grouptab;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team1.togather.domain.groupTab.ingrouptab.ChatRoom;
import team1.togather.dto.ChatMessageDto;
import team1.togather.repository.chat.ChatMessageRepository;
import team1.togather.repository.chat.ChatRepository;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRepository chatRepository;

    public void saveChatMessage(ChatMessageDto chatMessageDto) {
        ChatRoom chatRoom = chatRepository.getReferenceById(chatMessageDto.getChatRoomId());
        chatMessageRepository.save(chatMessageDto.toEntity(chatRoom));
    }
}

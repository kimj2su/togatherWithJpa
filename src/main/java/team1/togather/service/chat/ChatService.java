package team1.togather.service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team1.togather.dto.ChatRoomDto;
import team1.togather.dto.GroupTabWithMembersDto;
import team1.togather.repository.chat.ChatRepository;

import javax.persistence.EntityNotFoundException;


@Transactional
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRepository chatRepository;

    @Transactional(readOnly = true)
    public ChatRoomDto getChatRoomDto(Long chatRoomId) {
        return chatRepository.findById(chatRoomId)
                .map(ChatRoomDto::from)
                .orElseThrow(() -> new EntityNotFoundException("채팅방이 없습니다 - chatRoomId: " + chatRoomId));
    }

}

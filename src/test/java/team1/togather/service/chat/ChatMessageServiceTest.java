package team1.togather.service.chat;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import team1.togather.domain.groupTab.ingrouptab.ChatMessage;
import team1.togather.dto.ChatMessageDto;
import team1.togather.repository.chat.ChatMessageRepository;
import team1.togather.repository.chat.ChatRepository;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;

@DisplayName("채팅메세지 테스트")
@ExtendWith(MockitoExtension.class)
public class ChatMessageServiceTest {

    @InjectMocks
    private ChatMessageService sut;

    @Mock
    private ChatMessageRepository chatMessageRepository;
    @Mock
    private ChatRepository chatRepository;

    @DisplayName("채팅 메세지 저장 테스트")
    @Test
    void givenChatMessage_whenSavingChatMessage_thenSaveChatMessage() {
        //given
        ChatMessageDto chatMessageDto = createChatMessageDto();

        //when
        sut.saveChatMessage(chatMessageDto);

        //then
        then(chatMessageRepository).should().save(any(ChatMessage.class));
    }

    private ChatMessageDto createChatMessageDto() {
        ChatMessageDto chatMessageDto = ChatMessageDto.of(
                1L,
                "jisu",
                "content",
                LocalDateTime.now(),
                1L
        );
        return chatMessageDto;
    }
}

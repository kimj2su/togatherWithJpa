package team1.togather.service.chat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.test.util.ReflectionTestUtils;
import team1.togather.domain.groupTab.ingrouptab.ChatRoom;
import team1.togather.dto.ChatRoomDto;
import team1.togather.dto.GroupTabWithMembersDto;
import team1.togather.repository.chat.ChatRepository;
import team1.togather.security.configs.TestSecurityConfig;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("채팅 서비스 테스트")
@Import({TestSecurityConfig.class})
@ExtendWith(MockitoExtension.class)
public class ChatServiceTest {

    @InjectMocks
    private ChatService sut;
    @Mock
    private ChatRepository chatRepository;

    @DisplayName("채팅방이 없으면 예외를 던진다.")
    @Test
    void givenNotFoundChatRoomId_whenSearchingChatRoom_thenThrowsException() {
        //given
        Long chatRoomId = 0L;
        given(chatRepository.findById(chatRoomId)).willReturn(Optional.empty());

        //when
        Throwable t = catchThrowable(() -> sut.getChatRoomDto(chatRoomId));

        //then
        assertThat(t)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("채팅방이 없습니다 - chatRoomId: " + chatRoomId);
        then(chatRepository).should().findById(chatRoomId);
    }

    @DisplayName("채팅방 ID를 조회하면, 채팅방을 반환한다..")
    @Test
    void givenChatRoomId_whenSearchingChatRoom_thenReturnChatRoom() {
        //given
        Long chatRoomId = 1L;
        ChatRoom chatRoom = createChatRoom();
        given(chatRepository.findById(chatRoomId)).willReturn(Optional.of(chatRoom));

        //when
        ChatRoomDto chatRoomDto = sut.getChatRoomDto(chatRoomId);

        //then
        assertThat(chatRoomDto)
                .hasFieldOrPropertyWithValue("id", chatRoom.getId())
                .hasFieldOrPropertyWithValue("chatMessageDtos", chatRoom.getChatMessages());
        then(chatRepository).should().findById(chatRoomId);
    }

    private ChatRoom createChatRoom() {
        ChatRoom chatRoom = ChatRoom.of();
        ReflectionTestUtils.setField(chatRoom, "id", 1L);
        return chatRoom;
    }
}

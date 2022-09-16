package team1.togather.controller.grouptab.ingrouptab;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import team1.togather.dto.ChatRoomDto;
import team1.togather.security.configs.SecurityConfig;
import team1.togather.security.configs.annotation.WithMember;
import team1.togather.service.chat.ChatService;

import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@DisplayName("View 컨트롤러 - 채팅")
@WebMvcTest(controllers = ChatCRUDController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
class ChatCRUDControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ChatService chatService;

    @WithMember("jisu")
    @DisplayName("[view][GET] 채팅 페이지")
    @Test
    void givenChatRoomId_whenRequesting_thenReturnsNewChatRoomPage() throws Exception {
        // Given
        Long chatRoomId = 1L;
        String groupName = "테스트 그룹";
        given(chatService.getChatRoomDto(chatRoomId)).willReturn(createChatRoomDto());
        // When & Then
        mvc.perform(get("/chat")
                        .param("chatRoomId", String.valueOf(chatRoomId))
                        .param("groupName", groupName)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(model().attributeExists("chatRoomId"))
                .andExpect(model().attributeExists("groupName"))
                .andExpect(model().attributeExists("userName"))
                .andExpect(view().name("groupTabs/chat"));
    }

    private ChatRoomDto createChatRoomDto() {
        ChatRoomDto chatRoomDto = ChatRoomDto.of(1L, List.of());
        return chatRoomDto;
    }

}
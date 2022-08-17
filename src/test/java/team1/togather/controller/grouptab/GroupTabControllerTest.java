package team1.togather.controller.grouptab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;
import team1.togather.config.file.FileStore;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;
import team1.togather.dto.GroupTabDto;
import team1.togather.dto.request.GroupTabRequestDto;
import team1.togather.security.configs.TestSecurityConfig;
import team1.togather.security.configs.annotation.WithMember;
import team1.togather.security.configs.annotation.WithOauth2Member;
import team1.togather.service.GroupTabService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 그룹")
@Import({TestSecurityConfig.class})
@WebMvcTest(GroupTabController.class)
class GroupTabControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private GroupTabService groupTabService;
    @MockBean
    private FileStore fileStore;

    @WithMockUser
    @DisplayName("[view][GET] 새 모임 개설 페이지")
    @Test
    void givenNothing_whenRequesting_thenReturnsNewGroupTabPage() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/groupTabs/new"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("groupTabs/createGroupTabForm"));
    }


    @WithMember(value = "jisu1")
    @DisplayName("[view][POST] 일반 폼 로그인 유저 새 모임 개설 - 정상 호출")
    @Test
    void givenBasicMemberAndNewGroupTabInfo_whenRequesting_thenSavesNewGroupTab() throws Exception {
        // Given
        GroupTabRequestDto groupTabRequestDto = createGroupTabRequestDto();

        willDoNothing().given(groupTabService).saveGroupTab(any(GroupTabDto.class));

        // When & Then
        mvc.perform(
                        multipart("/groupTabs/new")
                                .file("attachFile", groupTabRequestDto.getAttachFile().getBytes())
                                .param("groupLocation", groupTabRequestDto.getGroupLocation())
                                .param("groupName", groupTabRequestDto.getGroupName())
                                .param("groupIntro", groupTabRequestDto.getGroupIntro())
                                .param("interest", groupTabRequestDto.getInterest())
                                .param("memberLimit", String.valueOf(groupTabRequestDto.getMemberLimit()))
                                .with(requestPostProcessor -> { // 3
                                    requestPostProcessor.setMethod("POST");
                                    return requestPostProcessor;
                                })
                                .contentType(MediaType.MULTIPART_FORM_DATA) // 4
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(redirectedUrl("/"));

        then(groupTabService).should().saveGroupTab(any(GroupTabDto.class));
    }

    @WithOauth2Member
    @DisplayName("[view][POST] Oauth2 로그인 멤버 새 모임 개설 - 정상 호출")
    @Test
    void givenOauth2MemberAndNewGroupTabInfo_whenRequesting_thenSavesNewGroupTab() throws Exception {
        // Given
        GroupTabRequestDto groupTabRequestDto = createGroupTabRequestDto();

        willDoNothing().given(groupTabService).saveGroupTab(any(GroupTabDto.class));

        // When & Then
        mvc.perform(
                        multipart("/groupTabs/new")
                                .file("attachFile", groupTabRequestDto.getAttachFile().getBytes())
                                .param("groupLocation", groupTabRequestDto.getGroupLocation())
                                .param("groupName", groupTabRequestDto.getGroupName())
                                .param("groupIntro", groupTabRequestDto.getGroupIntro())
                                .param("interest", groupTabRequestDto.getInterest())
                                .param("memberLimit", String.valueOf(groupTabRequestDto.getMemberLimit()))
                                .with(requestPostProcessor -> { // 3
                                    requestPostProcessor.setMethod("POST");
                                    return requestPostProcessor;
                                })
                                .contentType(MediaType.MULTIPART_FORM_DATA) // 4
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(redirectedUrl("/"));

        then(groupTabService).should().saveGroupTab(any(GroupTabDto.class));
    }

    private GroupTabRequestDto createGroupTabRequestDto() throws IOException {
        return GroupTabRequestDto.of("서울", "테스트 모임", "테스트 모임 소개","관심사",10, createFile());
    }

    private MockMultipartFile createFile() throws IOException {
        return new MockMultipartFile("image",
                "test.png",
                MediaType.IMAGE_PNG_VALUE,
                new FileInputStream("C:/Users/kjs76/Desktop/jisu/file/test.png"));
    }
}
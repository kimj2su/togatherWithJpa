package team1.togather.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import team1.togather.config.file.FileStore;
import team1.togather.domain.group.UploadFile;
import team1.togather.dto.GroupTabDto;
import team1.togather.dto.request.GroupTabRequestDto;
import team1.togather.security.auth.PrincipalDetailsService;
import team1.togather.security.config.SecurityConfigTest;
import team1.togather.service.GroupTabService;
import team1.togather.util.FormDataEncoder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@DisplayName("View 컨트롤러 - 그룹")
@Import({SecurityConfigTest.class, FormDataEncoder.class})
@WebMvcTest(GroupTabController.class)
class GroupTabControllerTest {

    private final MockMvc mvc;
    private final FormDataEncoder formDataEncoder;

    @MockBean
    private GroupTabService groupTabService;

    @MockBean
    private FileStore fileStore;

    public GroupTabControllerTest(
            @Autowired MockMvc mvc,
            @Autowired FormDataEncoder formDataEncoder
    ) {
        this.mvc = mvc;
        this.formDataEncoder = formDataEncoder;
    }

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

    @WithUserDetails(value = "jisu@email.com")
    @DisplayName("[view][POST] 새 모임 개설 - 정상 호출")
    @Test
    void givenNewGroupTabInfo_whenRequesting_thenSavesNewGroupTab() throws Exception {
        // Given
        GroupTabRequestDto groupTabRequestDto =
                GroupTabRequestDto.of("서울", "테스트 모임", "테스트 모임 소개","관심사",10,createFile() );

        willDoNothing().given(groupTabService).saveGroupTab(any(GroupTabDto.class));
//        System.out.println("formDataEncoder.encode(articleRequest) = " + formDataEncoder.encode(groupTabRequestDto));
        // When & Then
        mvc.perform(
                        multipart("/groupTabs/new")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                                .content(formDataEncoder.encode(groupTabRequestDto))
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(redirectedUrl("/"));
        then(groupTabService).should().saveGroupTab(any(GroupTabDto.class));
    }

    private MockMultipartFile createFile() throws IOException {
        return new MockMultipartFile("image",
                "test.png",
                MediaType.IMAGE_PNG_VALUE,
                new FileInputStream("C:/Users/kjs76/Desktop/jisu/file/test.png"));
    }
}
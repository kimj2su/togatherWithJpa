package team1.togather.controller.grouptab;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import team1.togather.config.file.FileStore;
import team1.togather.domain.groupTab.UploadFile;
import team1.togather.domain.groupTab.ingrouptab.MemberGrade;
import team1.togather.dto.GroupTabDto;
import team1.togather.dto.MemberDto;
import team1.togather.dto.GroupTabWithMembersDto;
import team1.togather.dto.MemberInGroupTabDto;
import team1.togather.dto.request.GroupTabRequestDto;
import team1.togather.security.configs.SecurityConfig;
import team1.togather.security.configs.TestSecurityConfig;
import team1.togather.security.configs.annotation.WithMember;
import team1.togather.security.configs.annotation.WithOauth2Member;
import team1.togather.service.PaginationService;
import team1.togather.service.grouptab.GroupTabService;
import team1.togather.service.grouptab.MemberInGroupTabService;
import team1.togather.service.member.CategoryService;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 그룹")
@WebMvcTest(controllers = GroupTabController.class,
        excludeFilters = { //!Added!
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class) })
class GroupTabControllerTest {


    @Autowired
    private MockMvc mvc;
    @MockBean
    private GroupTabService groupTabService;
    @MockBean
    private MemberInGroupTabService memberInGroupTabService;
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private PaginationService paginationService;

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
                .andExpect(model().attributeExists("intOut"))
                .andExpect(view().name("groupTabs/createGroupTabForm"));
    }

    @WithMember(value = "jisu1")
    @DisplayName("[view][POST] 일반 폼 로그인 유저 새 모임 개설 - 정상 호출")
    @Test
    void givenBasicMemberAndNewGroupTabInfo_whenRequesting_thenSavesNewGroupTab() throws Exception {
        // Given
        GroupTabRequestDto groupTabRequestDto = createGroupTabRequestDto();

        given(groupTabService.saveGroupTab(any(GroupTabDto.class))).willReturn(anyLong());

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
                .andExpect(status().is3xxRedirection());

        then(groupTabService).should().saveGroupTab(any(GroupTabDto.class));
    }

    @WithOauth2Member
    @DisplayName("[view][POST] Oauth2 로그인 멤버 새 모임 개설 - 정상 호출")
    @Test
    void givenOauth2MemberAndNewGroupTabInfo_whenRequesting_thenSavesNewGroupTab() throws Exception {
        // Given
        GroupTabRequestDto groupTabRequestDto = createGroupTabRequestDto();

        given(groupTabService.saveGroupTab(any(GroupTabDto.class))).willReturn(anyLong());

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
                .andExpect(status().is3xxRedirection());

        then(groupTabService).should().saveGroupTab(any(GroupTabDto.class));
    }

    @DisplayName("[view][GET] 모임 페이지 - 인증 없을 땐 로그인 페이지로 이동")
    @Test
    void givenNothing_whenRequestingGroupTabPage_thenRedirectsToLoginPage() throws Exception {
        // Given
        Long groupTabId = 1L;

        // When & Then
        mvc.perform(get("/groupTabs/" + groupTabId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
        then(groupTabService).shouldHaveNoInteractions();
    }

    @WithMember(value = "KJS")
    @DisplayName("view - get 모임 상세 페이지 - 정상 호출, 인증된 사용자")
    @Test
    void givenNothing_whenRequestingGroupTabView_thenReturnsGroupTabView() throws Exception {
        // given
        Long groupTabId = 1L;
        Long memberId = 1L;
        String userId= "KJS";

        given(groupTabService.getGroupTabWithMembers(groupTabId)).willReturn(createGroupTabWithMembersDto());
        MemberInGroupTabDto memberInGroupTabDto = createMemberInGroupTabDto();
        given(memberInGroupTabService.searchMemberInGroupTab(groupTabId, memberId)).willReturn(createMemberInGroupTabDto());

        // when & then
        mvc.perform(get("/groupTabs/" + groupTabId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("groupTabs/detail"))
                .andExpect(model().attributeExists("groupTab"))
                .andExpect(model().attributeExists("membersNameList"));
        then(groupTabService).should().getGroupTabWithMembers(groupTabId);
        then(memberInGroupTabService).should().searchMemberInGroupTab(groupTabId, memberId);
        assertThat(memberInGroupTabService.searchMemberInGroupTab(groupTabId, memberId)).isNotNull();
    }

    private MemberInGroupTabDto createMemberInGroupTabDto() {
        return MemberInGroupTabDto.from(MemberGrade.GROUP_MASTER);
    }
    private List<MemberInGroupTabDto> createMemberInGroupTab() {
        return List.of();
    }

    private GroupTabWithMembersDto createGroupTabWithMembersDto() {
        return GroupTabWithMembersDto.of(
                1L,
                "테스트 모임",
                "테스트 모임 이름",
                "테스트 모임 소개",
                "테스트 모임 관심사",
                10,
                createUploadFile(),
                createMemberDto(),
                Set.of(),
                LocalDateTime.now(),
                "jisu",
                LocalDateTime.now(),
                "jisu"
        );
    }

    private MemberDto createMemberDto() {
        return MemberDto.of(
                1L,
                "jisu@email.com",
                "password",
                "jisu",
                "jisu1",
                "2022-08-11",
                "M",
                "category_first",
                "category_second",
                "category_third"
        );
    }

    private UploadFile createUploadFile() {
        return new UploadFile("테스트 파일 이름", "테스트 서버 저장 파일 이름");
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
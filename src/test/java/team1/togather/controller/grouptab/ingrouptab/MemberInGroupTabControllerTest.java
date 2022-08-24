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
import team1.togather.dto.MemberInGroupTabDto;
import team1.togather.dto.request.MemberInGroupTabsRequestDto;
import team1.togather.security.configs.SecurityConfig;
import team1.togather.security.configs.annotation.WithMember;
import team1.togather.security.configs.annotation.WithOauth2Member;
import team1.togather.service.GroupTabService;
import team1.togather.service.MemberInGroupTabService;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 그룹내 멤버")
@WebMvcTest(controllers = MemberInGroupTabController.class,
        excludeFilters = { //!Added!
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class) })
class MemberInGroupTabControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private MemberInGroupTabService memberInGroupTabService;
    @MockBean
    private GroupTabService groupTabService;

    @WithMember(value = "jisu")
    @DisplayName("[view][POST] 일반 폼 로그인 유저 모임 가입하기")
    @Test
    void givenBasicMember_whenRequesting_thenSavesNewMemberInGroupTab() throws Exception {
        // Given
        Long groupTabId = 1L;
        Long grade = 3L;
        MemberInGroupTabsRequestDto memberInGroupTabsRequestDto = createMemberInGroupTabsRequestDto(groupTabId, grade);

        willDoNothing().given(memberInGroupTabService).saveMemberInGroupTab(any(MemberInGroupTabDto.class));

        // When & Then
        mvc.perform(
                        post("/mig/new/" + groupTabId)
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("groupTabId", String.valueOf(memberInGroupTabsRequestDto.getGroupTabId()))
                                .param("gradeNumber", String.valueOf(memberInGroupTabsRequestDto.getGradeNumber()))
                                .with(csrf())
                )
                .andExpect(status().isOk());
        then(memberInGroupTabService).should().saveMemberInGroupTab(any(MemberInGroupTabDto.class));
    }

    @WithMember(value = "jisu")
    @DisplayName("[view][POST] 일반 폼 로그인 유저 모임 탈퇴하기")
    @Test
    void givenBasicMember_whenRequesting_thenDeleteMemberInGroupTab() throws Exception {
        // Given
        Long groupTabId = 1L;
        Long grade = 2L;
        Long memberId = 1L;
        MemberInGroupTabsRequestDto memberInGroupTabsRequestDto = createMemberInGroupTabsRequestDto(groupTabId, grade);

        willDoNothing().given(memberInGroupTabService).saveMemberInGroupTab(any(MemberInGroupTabDto.class));

        // When & Then
        mvc.perform(
                        post("/mig/delete/" + groupTabId)
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("groupTabId", String.valueOf(memberInGroupTabsRequestDto.getGroupTabId()))
                                .param("gradeNumber", String.valueOf(memberInGroupTabsRequestDto.getGradeNumber()))
                                .with(csrf())
                )
                .andExpect(status().isOk());
        then(memberInGroupTabService).should().deleteMemberInGroupTab(groupTabId, memberId);
    }

    @WithOauth2Member
    @DisplayName("[view][POST] OAuth2 유저 모임 가입하기")
    @Test
    void givenOAuth2Member_whenRequesting_thenSavesNewMemberInGroupTab() throws Exception {
        // Given
        Long groupTabId = 1L;
        Long grade = 3L;
        MemberInGroupTabsRequestDto memberInGroupTabsRequestDto = createMemberInGroupTabsRequestDto(groupTabId, grade);

        willDoNothing().given(memberInGroupTabService).saveMemberInGroupTab(any(MemberInGroupTabDto.class));

        // When & Then
        mvc.perform(
                        post("/mig/new/" + groupTabId)
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("groupTabId", String.valueOf(memberInGroupTabsRequestDto.getGroupTabId()))
                                .param("gradeNumber", String.valueOf(memberInGroupTabsRequestDto.getGradeNumber()))
                                .with(csrf())
                )
                .andExpect(status().isOk());
        then(memberInGroupTabService).should().saveMemberInGroupTab(any(MemberInGroupTabDto.class));
    }

    @WithOauth2Member
    @DisplayName("[view][POST] OAuth2 유저 모임 탈퇴하기")
    @Test
    void givenOAuth2Member_whenRequesting_thenDeleteMemberInGroupTab() throws Exception {
        // Given
        Long groupTabId = 1L;
        Long grade = 2L;
        Long memberId = 1L;
        MemberInGroupTabsRequestDto memberInGroupTabsRequestDto = createMemberInGroupTabsRequestDto(groupTabId, grade);

        willDoNothing().given(memberInGroupTabService).saveMemberInGroupTab(any(MemberInGroupTabDto.class));

        // When & Then
        mvc.perform(
                        post("/mig/delete/" + groupTabId)
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("groupTabId", String.valueOf(memberInGroupTabsRequestDto.getGroupTabId()))
                                .param("gradeNumber", String.valueOf(memberInGroupTabsRequestDto.getGradeNumber()))
                                .with(csrf())
                )
                .andExpect(status().isOk());
        then(memberInGroupTabService).should().deleteMemberInGroupTab(groupTabId, memberId);

    }

    private MemberInGroupTabsRequestDto createMemberInGroupTabsRequestDto(Long groupTabId, Long grade) {
        return MemberInGroupTabsRequestDto.of(groupTabId, grade);
    }


}
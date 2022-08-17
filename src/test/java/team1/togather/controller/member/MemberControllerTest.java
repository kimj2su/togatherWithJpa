package team1.togather.controller.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import team1.togather.domain.member.Role;
import team1.togather.dto.MemberDto;
import team1.togather.dto.request.MemberRequestDto;
import team1.togather.security.configs.TestSecurityConfig;
import team1.togather.service.MemberService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayName("view 컨트롤러 - 멤버")
@Import({TestSecurityConfig.class,})
@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;

    @DisplayName("view - get 회원가입 페이지 - 정상 호출")
    @Test
    void givenNothing_whenRequestingMemberView_thenReturnMemberView() throws Exception{
        // given

        // when & then
        mvc.perform(get("/members/new"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("view - post 회원가입 - 정살 호출")
    @Test
    void givenNewMemberInfo_whenRequesting_thenSaveNewMember() throws Exception {
        // given
        MemberRequestDto memberRequestDto = createMemberRequestDto();
        willDoNothing().given(memberService).saveMember(memberRequestDto.toDto());

        // when & then
        mvc.perform(
                post("/members/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", memberRequestDto.getUsername())
                        .param("userId", memberRequestDto.getUserId())
                        .param("password", memberRequestDto.getPassword())
                        .param("email", memberRequestDto.getEmail())
                        .param("birth", memberRequestDto.getBirth())
                        .param("gender", memberRequestDto.getGender())
                        .param("category_first", memberRequestDto.getCategory_first())
                        .param("category_second", memberRequestDto.getCategory_second())
                        .param("category_third", memberRequestDto.getCategory_third())
                        .with(csrf())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(redirectedUrl("y/"));
        then(memberService).should().saveMember(any(MemberDto.class));
    }

    private MemberRequestDto createMemberRequestDto() {
        return new MemberRequestDto(
                "김지수",
                "jisu1",
                "1234",
                "jisu@email.com",
                "2022-08-07",
                "M",
                "category_first",
                "category_second",
                "category_third",
                roles()
        );
    }
    private Set<Role> roles() {
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleName("ROLE_USER");
        role.setRoleDesc("사용자권한");
        roles.add(role);
        return roles;
    }

//    private Role findRoleUser() {
//        Role roleUser = roleRepository.findByRoleName("ROLE_USER");
//        return roleUser;
//    }
}
package team1.togather.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;
import team1.togather.dto.MemberDto;
import team1.togather.repository.MemberRepository;
import team1.togather.repository.RoleRepository;
import team1.togather.security.configs.SecurityConfig;
import team1.togather.service.member.MemberService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.BDDMockito.given;

@DisplayName("멤버 테스트")
@ExtendWith(MockitoExtension.class)
@Import(SecurityConfig.class)
class MemberServiceTest {

    @InjectMocks private MemberService sut;

    @Mock private MemberRepository memberRepository;
    @Mock private RoleRepository roleRepository;
    @Mock private PasswordEncoder passwordEncoder; //추가안해주면 MemberService에서 encode하는 과정에서 npe뜸

    @DisplayName("멤버 정보를 입력하면 회원을 생성한다.")
    @Test
    void givenMemberInfo_whenSavingMember_thenSavesMember() {
        //given
        MemberDto newMemberDto = createNewMemberDto();
        Member newMember = createNewMember();
        given(memberRepository.save(any(Member.class))).willReturn(newMember);
        //newMember가 아니라 createNewMember() 했을때 you are stubbing the behaviour of another mock inside before 'thenReturn' instruction is completed 에러가남
        //when
        sut.saveMember(newMemberDto);

        //then
        then(memberRepository).should().save(any(Member.class));
    }

    private MemberDto createNewMemberDto() {
        return createNewMemberDto(
                1L,
                "김지수",
                "jisu1",
                "1234",
                "jisu@email.com",
                "2022-08-07",
                "M",
                "category_first",
                "category_second",
                "category_third"
        );
    }

    private MemberDto createNewMemberDto(Long memberId, String username, String userId, String password, String email, String birth, String gender, String category_first, String category_second, String category_third) {
        return MemberDto.of(
                memberId, username, userId, password, email, birth, gender, category_first, category_second, category_third
        );
    }


    private Member createNewMember() {
        Member of = Member.of(
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
        return of;
    }

    private Set<Role> roles() {
        Set<Role> roles = new HashSet<>();
        roles.add(findRoleUser());
        return roles;
    }

    private Role findRoleUser() {
        Role roleUser = roleRepository.findByRoleName("ROLE_USER");
        return roleUser;
    }


}
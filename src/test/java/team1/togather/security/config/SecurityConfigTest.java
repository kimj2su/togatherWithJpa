package team1.togather.security.config;

import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;
import team1.togather.dto.MemberDto;
import team1.togather.repository.MemberRepository;
import team1.togather.repository.RoleRepository;
import team1.togather.security.auth.PrincipalDetails;
import team1.togather.security.auth.oauth2.PrincipalOauth2UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class SecurityConfigTest {

    @MockBean
    private PrincipalOauth2UserService principalOauth2UserService;
    @MockBean
    private MemberRepository memberRepository;
    @MockBean
    private RoleRepository roleRepository;


    @Bean
    public UserDetailsService userDetailsService(MemberRepository memberRepository) {
        return email -> memberRepository
                .findByEmailAndProvider(email)
                .map(MemberDto::from)
                .map(PrincipalDetails::from)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다 - email: " + email));
    }
    @PostConstruct
    public void securitySetup() {

        Member of = Member.of(
                "jisu@email.com",
                "password",
                "jisu",
                "nickname",
                "2022-08-11",
                "M",
                "010-3316-3268",
                "category_first",
                "category_second",
                "category_third",
                roles()
        );
        given(memberRepository.findById(ArgumentMatchers.anyLong())).willReturn(Optional.of(of));
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
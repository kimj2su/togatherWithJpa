package team1.togather.security.configs;

import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;
import team1.togather.repository.MemberRepository;
import team1.togather.repository.RoleRepository;
import team1.togather.security.auth.PrincipalDetailsService;
import team1.togather.security.auth.oauth2.PrincipalOauth2UserService;
import team1.togather.security.handler.FormAuthenticationFailureHandler;
import team1.togather.security.handler.FormCustomAuthenticationSuccessHandler;
import team1.togather.service.SecurityResourceService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean
    private FormCustomAuthenticationSuccessHandler formAuthenticationSuccessHandler;

    @MockBean
    private FormAuthenticationFailureHandler formAuthenticationFailureHandler;

    @MockBean
    private SecurityResourceService securityResourceService;

    @MockBean
    private PrincipalDetailsService principalDetailsService;

    @MockBean
    private PrincipalOauth2UserService principalOauth2UserService;

}
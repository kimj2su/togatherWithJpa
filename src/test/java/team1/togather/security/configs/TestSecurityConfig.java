package team1.togather.security.configs;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import team1.togather.security.auth.PrincipalDetailsService;
import team1.togather.security.auth.oauth2.PrincipalOauth2UserService;
import team1.togather.security.handler.FormAuthenticationFailureHandler;
import team1.togather.security.handler.FormCustomAuthenticationSuccessHandler;
import team1.togather.service.SecurityResourceService;

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
package team1.togather.security.config;

import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import team1.togather.security.auth.oauth2.PrincipalOauth2UserService;

import static org.junit.jupiter.api.Assertions.*;
@Import(SecurityConfig.class)
public class SecurityConfigTest {

    @MockBean
    private PrincipalOauth2UserService principalOauth2UserService;
}
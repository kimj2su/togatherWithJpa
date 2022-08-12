package team1.togather.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import team1.togather.security.auth.oauth2.PrincipalOauth2UserService;
import team1.togather.security.handler.FormCustomAuthenticationSuccessHandler;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

//    private final PrincipalDetailsService principalDetailsService;
    private final PrincipalOauth2UserService principalOauth2UserService;

    private final FormCustomAuthenticationSuccessHandler formCustomAuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .mvcMatchers(
                                HttpMethod.GET,
                                "/",
                                "/members/*",
                                "/loginForm"
                        ).permitAll()
                        .mvcMatchers(
                                HttpMethod.POST,
                                "/members/*",
                                "/login"
                        ).permitAll()
                        .mvcMatchers(
                                HttpMethod.GET,
                                "/groupTabs/**"
                        ).authenticated()
                        .mvcMatchers(
                                HttpMethod.POST,
                                "/groupTabs/**"
                        ).authenticated()
                        .anyRequest().permitAll()
                )
                    .formLogin()
                    .loginPage("/loginForm")
                    .usernameParameter("email")
                    .passwordParameter("pwd")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/")
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                    .loginPage("/loginForm")
                    .defaultSuccessUrl("/members/new/oauth2")
    //                .successHandler(formCustomAuthenticationSuccessHandler)
                    .failureUrl("/loginForm")
                    .userInfoEndpoint()
                    .userService(principalOauth2UserService)
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
//todo : 이해도가 낮아 필터등록말고 WebSecurityConfigurerAdapter로 다시 설계해야 될 것 같다.
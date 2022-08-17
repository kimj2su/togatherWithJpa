package team1.togather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import team1.togather.domain.member.Member;
import team1.togather.security.auth.PrincipalDetails;

import java.util.Optional;


/**
 * AuditingFields의 들어가는 By컬럼에 시큐리티 인증객체에서 꺼내와 제가 정의한 PrincipalDetails의 getUername을 넣어준다.
 */
@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {

        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(PrincipalDetails.class::cast)
                .map(PrincipalDetails::getUsername);
    }
}


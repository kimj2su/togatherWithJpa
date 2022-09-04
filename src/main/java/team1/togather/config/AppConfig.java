package team1.togather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team1.togather.repository.member.ResourcesRepository;
import team1.togather.service.SecurityResourceService;

/**
 * SecurityResourceService를 빈으로 등록해주었다.
 */
@Configuration
class AppConfig {

    @Bean
    public SecurityResourceService securityResourceService(ResourcesRepository resourcesRepository) {
        return new SecurityResourceService(resourcesRepository);
    }

}

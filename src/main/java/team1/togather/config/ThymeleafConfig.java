package team1.togather.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

/**
 * 디커플링 로직을 사용하기 위해 등록하였다.
 * application.yml
 * thymeleaf3:
 *     decoupled-logic: true
 * 으로 사용함.
 * 사용 이유 => 기본 html 파일 위의 파일이름.th.xml로 디자인된 코드 위에 바로 작업 할 수 있다.
 */
@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(SpringResourceTemplateResolver defaultTemplateResolver, Thymeleaf3Properties thymeleaf3Properties) {
        defaultTemplateResolver.setUseDecoupledLogic(thymeleaf3Properties.isDecoupledLogic());
        return defaultTemplateResolver;
    }

    @RequiredArgsConstructor
    @Getter
    @ConstructorBinding
    @ConfigurationProperties("spring.thymeleaf3") // 유저가 직접만들었을 경우 스캔을 해줘야한다. main메서드위의 붙인다.
    public static class Thymeleaf3Properties {
        /**
         * User Thymeleaf 3 Decoupled Logic
         */
        private final boolean decoupledLogic;

    }

}

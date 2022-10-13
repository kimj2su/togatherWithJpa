package team1.togather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import team1.togather.security.interceptor.Oauth2LoginCheckInterceptor;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new Oauth2LoginCheckInterceptor())
//                .addPathPatterns("/members/new/oauth2") // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
//                .addPathPatterns("/groupTabs/*");
////                .excludePathPatterns("/**"); // 해당 경로는 인터셉터가 가로채지 않는다.
//    }
}

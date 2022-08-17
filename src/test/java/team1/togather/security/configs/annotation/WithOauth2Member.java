package team1.togather.security.configs.annotation;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomOAuth2AccountSecurityContextFactory.class)
public @interface WithOauth2Member {

    String username() default "username";

    String name() default "name";

    String email() default "my@default.email";

    String picture() default "https://get_my_picture.com";

    String role() default "ROLE_USER";

    String registrationId() default "google";
}

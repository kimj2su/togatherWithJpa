package team1.togather.security.configs.annotation;


import org.springframework.security.test.context.support.WithSecurityContext;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMemberSecurityContextFactory.class)
public @interface WithMember {
    String value();
}

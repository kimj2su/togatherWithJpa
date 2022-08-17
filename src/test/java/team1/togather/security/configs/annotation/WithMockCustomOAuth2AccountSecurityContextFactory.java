package team1.togather.security.configs.annotation;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;
import team1.togather.security.auth.PrincipalDetails;

import java.util.*;

public class WithMockCustomOAuth2AccountSecurityContextFactory implements WithSecurityContextFactory<WithOauth2Member> {
    @Override
    public SecurityContext createSecurityContext(WithOauth2Member withOauth2Member) {
        // 1
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        // 2
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("username", withOauth2Member.username());
        attributes.put("name", withOauth2Member.name());
        attributes.put("userId", withOauth2Member.name());
        attributes.put("email", withOauth2Member.email());
        attributes.put("picture", withOauth2Member.picture());

        Member of = Member.of(
                withOauth2Member.username(),
                withOauth2Member.username(),
                "1234",
                "jisu@email.com",
                "2022-08-07",
                "M",
                "category_first",
                "category_second",
                "category_third",
                roles("ROLE_USER", "사용자권한")
        );


        // 3
        PrincipalDetails principalDetails = new PrincipalDetails(of, attributes);

        // 4
        OAuth2AuthenticationToken token = new OAuth2AuthenticationToken(
                principalDetails,
                principalDetails.getAuthorities(),
                withOauth2Member.registrationId());

        // 5
        context.setAuthentication(token);
        return context;
    }
    private Set<Role> roles(String roleName, String roleDesc) {
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleName(roleName);
        role.setRoleDesc(roleDesc);
        roles.add(role);
        return roles;
    }

}

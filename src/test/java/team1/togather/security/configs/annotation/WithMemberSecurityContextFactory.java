package team1.togather.security.configs.annotation;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;
import team1.togather.security.auth.PrincipalDetails;

import java.util.HashSet;
import java.util.Set;


public class WithMemberSecurityContextFactory implements WithSecurityContextFactory<WithMember> {

    @Override
    public SecurityContext createSecurityContext(WithMember withMember) {
        String userId = withMember.value();

        Member of = Member.of(
                1L,
                "김지수",
                userId,
                "1234",
                "jisu@email.com",
                "2022-08-07",
                "M",
                "category_first",
                "category_second",
                "category_third",
                roles("ROLE_USER", "사용자권한")
        );
        PrincipalDetails principalDetails = new PrincipalDetails(of);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        principalDetails,
                        null,
                        principalDetails.getAuthorities());

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
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

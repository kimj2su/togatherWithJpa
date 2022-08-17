package team1.togather.security.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Resources;
import team1.togather.domain.member.Role;
import team1.togather.domain.member.RoleHierarchy;
import team1.togather.repository.MemberRepository;
import team1.togather.repository.ResourcesRepository;
import team1.togather.repository.RoleHierarchyRepository;
import team1.togather.repository.RoleRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final MemberRepository memberRepository;

    private final RoleRepository roleRepository;
    private final RoleHierarchyRepository roleHierarchyRepository;

    private final ResourcesRepository resourcesRepository;

    private final PasswordEncoder passwordEncoder;


    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }

        setupSecurityResources();

        alreadySetup = true;
    }



    private void setupSecurityResources() {
        Set<Role> adminRoles = new HashSet<>();
        Set<Role> managerRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();

        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", "관리자");
        Role managerRole = createRoleIfNotFound("ROLE_MANAGER", "매니저권한");
        Role userRole = createRoleIfNotFound("ROLE_USER", "사용자권한");

        adminRoles.add(adminRole);
        managerRoles.add(managerRole);
        userRoles.add(userRole);

        createResourceIfNotFound("/admin/**", "", adminRoles, "url");
        createResourceIfNotFound("/member/new", "", userRoles, "url");
        createResourceIfNotFound("/groupTabs/**", "", userRoles, "url");

        createUserIfNotFound("김지수1", "jisu1", "1234","admin@gmail.com",
                "1996-05-14", "M",  "독서", "게임", "개발", adminRoles);
        createUserIfNotFound("김지수2", "jisu2", "1234","manager@gmail.com",
                "1996-05-14", "M", "독서", "게임", "개발", managerRoles);
        createUserIfNotFound("김지수3", "jisu3", "1234","user@gmail.com",
                "1996-05-14", "M", "독서", "게임", "개발", userRoles);

        createRoleHierarchyIfNotFound(managerRole, adminRole);
        createRoleHierarchyIfNotFound(userRole, managerRole);
    }

    @Transactional
    public Role createRoleIfNotFound(String roleName, String roleDesc) {

        Role role = roleRepository.findByRoleName(roleName);

        if (role == null) {
            role = Role.builder()
                    .roleName(roleName)
                    .roleDesc(roleDesc)
                    .build();
        }
        return roleRepository.save(role);
    }

    @Transactional
    public Member createUserIfNotFound(final String username, final String userId, final String password, final String email,
                                       final String birth, final String gender, final String category_first,
                                       final String category_second, final String category_third, Set<Role> roleSet) {

        Optional<Member> findmember = memberRepository.findByUserId(userId);


        if (findmember.isEmpty() || findmember == null) {
            findmember = Optional.ofNullable(Member.builder()
                    .username(username)
                    .userId(userId)
                    .password(passwordEncoder.encode(password))
                    .email(email)
                    .birth(birth)
                    .gender(gender)
                    .category_first(category_first)
                    .category_second(category_second)
                    .category_third(category_third)
                    .memberRoles(roleSet)
                    .build());
        }
        return memberRepository.save(findmember.get());
    }

    @Transactional
    public Resources createResourceIfNotFound(String resourceName, String httpMethod, Set<Role> roleSet, String resourceType) {
        Resources resources = resourcesRepository.findByResourceNameAndHttpMethod(resourceName, httpMethod);

        if (resources == null) {
            resources = Resources.builder()
                    .resourceName(resourceName)
                    .httpMethod(httpMethod)
                    .orderNum(count.incrementAndGet())
                    .resourceType(resourceType)
                    .roleSet(roleSet)
                    .build();
        }
        return resourcesRepository.save(resources);
    }

    @Transactional
    public void createRoleHierarchyIfNotFound(Role childRole, Role parentRole) {

        RoleHierarchy roleHierarchy = roleHierarchyRepository.findByChildName(parentRole.getRoleName());
        if (roleHierarchy == null) {
            roleHierarchy = RoleHierarchy.builder()
                    .childName(parentRole.getRoleName())
                    .build();
        }
        RoleHierarchy parentRoleHierarchy = roleHierarchyRepository.save(roleHierarchy);

        roleHierarchy = roleHierarchyRepository.findByChildName(childRole.getRoleName());
        if (roleHierarchy == null) {
            roleHierarchy = RoleHierarchy.builder()
                    .childName(childRole.getRoleName())
                    .build();
        }

        RoleHierarchy childRoleHierarchy = roleHierarchyRepository.save(roleHierarchy);
        childRoleHierarchy.setParentName(parentRoleHierarchy);
    }

}
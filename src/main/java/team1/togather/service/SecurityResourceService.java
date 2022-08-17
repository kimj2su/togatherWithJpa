package team1.togather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import team1.togather.domain.member.Resources;
import team1.togather.repository.ResourcesRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RequiredArgsConstructor
public class SecurityResourceService {

    private final ResourcesRepository resourcesRepository;

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {

        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
        List<Resources> resourcesList = resourcesRepository.findAllResources(); // 디비에 있는 모든 자원을가져온다. 여기에는 리소스(자원)마다 여러개의 롤이있다. 그것을 다 가져온다.
        resourcesList.forEach(re -> {
            List<ConfigAttribute> configAttributes = new ArrayList<>();
            re.getRoleSet().forEach(role -> {

                configAttributes.add(new SecurityConfig(role.getRoleName()));
            });
            result.put(new AntPathRequestMatcher(re.getResourceName()), configAttributes);

        });
        return result;
    }
    
}

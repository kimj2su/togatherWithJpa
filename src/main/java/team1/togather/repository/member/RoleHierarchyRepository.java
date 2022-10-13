package team1.togather.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import team1.togather.domain.member.RoleHierarchy;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {

    RoleHierarchy findByChildName(String roleName);
}

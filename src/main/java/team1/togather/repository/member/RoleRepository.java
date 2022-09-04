package team1.togather.repository.member;


import org.springframework.data.jpa.repository.JpaRepository;
import team1.togather.domain.member.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String name);

    @Override
    void delete(Role role);
}

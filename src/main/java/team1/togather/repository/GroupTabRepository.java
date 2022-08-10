package team1.togather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team1.togather.domain.group.GroupTab;

public interface GroupTabRepository extends JpaRepository<GroupTab, Long> {
}

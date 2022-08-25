package team1.togather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team1.togather.domain.gathring.Gathering;

public interface GatheringRepository extends JpaRepository<Gathering, Long> {
}

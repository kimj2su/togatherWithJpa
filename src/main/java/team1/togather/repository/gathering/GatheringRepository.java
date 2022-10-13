package team1.togather.repository.gathering;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import team1.togather.domain.gathring.Gathering;
import team1.togather.domain.groupTab.GroupTab;

import java.util.List;

public interface GatheringRepository extends JpaRepository<Gathering, Long> {

    @Override
    @EntityGraph(attributePaths = {"groupTab", "member"})
    List<Gathering> findAll();

    void deleteById(Long gatheringId);
}

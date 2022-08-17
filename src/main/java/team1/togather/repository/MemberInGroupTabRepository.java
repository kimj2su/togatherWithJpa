package team1.togather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team1.togather.domain.groupTab.ingrouptab.MemberInGroupTab;

import java.util.List;

public interface MemberInGroupTabRepository extends JpaRepository<MemberInGroupTab, Long> {
    List<MemberInGroupTab> findByGroupTab_Id(Long groupTabId);

    void deleteByIdAndMember_UserId(Long groupTabId, String userId);
}

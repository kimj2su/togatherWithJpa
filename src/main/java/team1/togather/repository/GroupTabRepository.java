package team1.togather.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team1.togather.domain.groupTab.GroupTab;

import java.util.List;

public interface GroupTabRepository extends JpaRepository<GroupTab, Long>, GroupTabRepositoryCustom {

    Page<GroupTab> findGroupTab(Pageable pageable);
    void deleteByIdAndMember_UserId(Long articleId, String userId);

    Page<GroupTab> searchGroupTabByInterest(String searchValue, Pageable pageable);

}

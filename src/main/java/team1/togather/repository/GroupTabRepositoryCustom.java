package team1.togather.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team1.togather.domain.groupTab.GroupTab;

public interface GroupTabRepositoryCustom {

    Page<GroupTab> MembersGroupTabs(Pageable pageable, String category_first, String category_second, String category_third);
}

package team1.togather.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team1.togather.domain.groupTab.GroupTab;

public interface SearchGroupTabs {

    Page<GroupTab> SearchGroupTabs(String groupName, String intOut, String groupLocation, Pageable pageable);
}

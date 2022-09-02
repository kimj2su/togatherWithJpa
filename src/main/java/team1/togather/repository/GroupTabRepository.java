package team1.togather.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team1.togather.domain.groupTab.GroupTab;

import java.util.List;

public interface GroupTabRepository extends JpaRepository<GroupTab, Long>, GroupTabRepositoryCustom {


    void deleteByIdAndMember_UserId(Long articleId, String userId);

    @Query("select g from GroupTab g where g.category.id in :categoryId")
    Page<GroupTab> findGroupTabByCategory(List<Long> categoryId, Pageable pageable);

    Page<GroupTab> findGroupTabsByCategory_IdIn(List<Long> categoryId, Pageable pageable);



    @Override
    @EntityGraph(attributePaths = {"groupUploadFile"})
    Page<GroupTab> findAll(Pageable pageable);

}

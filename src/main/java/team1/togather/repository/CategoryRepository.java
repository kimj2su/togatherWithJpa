package team1.togather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team1.togather.domain.member.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select distinct c.intOut from Category c")
    List<String> findByIntOut();

    @Query("select distinct c.intIn from Category c where c.intOut= :intOut")
    List<String> findByIntIn(@Param("intOut") String intOut);

    @Query("select distinct c.firstOption from Category c where c.intIn= :intIn")
    List<String> findByFirstOption(@Param("intIn") String intIn);

    @Query("select c.id from Category c where c.intOut= :intOut")
    List<Long> findCategoryByIntOut(@Param("intOut") String intOut);

    @Query("select c from Category c where c.intIn= :intIn")
    Category findCategoryByIntIn(@Param("intIn") String intIn);

    @Query("select c from Category c where c.firstOption= :firstOption")
    Category findCategoryByFirstOption(@Param("firstOption") String firstOption);

}

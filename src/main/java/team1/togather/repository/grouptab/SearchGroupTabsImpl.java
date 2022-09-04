package team1.togather.repository.grouptab;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;
import team1.togather.domain.groupTab.GroupTab;

import java.util.List;

import static team1.togather.domain.groupTab.QGroupTab.groupTab;
import static team1.togather.domain.member.QCategory.category;

@RequiredArgsConstructor
public class SearchGroupTabsImpl implements SearchGroupTabs{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<GroupTab> SearchGroupTabs(String groupName, String intOut, String groupLocation, Pageable pageable) {
        List<Long> categoriesId = getCategoriesId(intOut);
        List<GroupTab> groupTabs = getGroupTabs(groupName, categoriesId, groupLocation, pageable);
        JPAQuery<Long> countQuery = getTotal(groupName, categoriesId, groupLocation);
        return PageableExecutionUtils.getPage(groupTabs, pageable, countQuery::fetchOne);
    }

    private List<Long> getCategoriesId(String intOut) {
        return jpaQueryFactory.select(category.id)
                .from(category)
                .where(eqIntOut(intOut))
                .fetch();
    }
    private List<GroupTab> getGroupTabs(String groupName, List<Long> categoriesId, String groupLocation, Pageable pageable) {
        return jpaQueryFactory.selectFrom(groupTab)
                .where(eqGroupName(groupName), eqCategoriesId(categoriesId), eqGroupLocation(groupLocation))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private JPAQuery<Long> getTotal(String groupName,  List<Long> categoriesId, String groupLocation) {
        return jpaQueryFactory
                .select(groupTab.count())
                .from(groupTab)
                .where(eqGroupName(groupName), eqCategoriesId(categoriesId), eqGroupLocation(groupLocation));
    }

    private BooleanExpression eqIntOut(String intOut) {
        if (!StringUtils.hasText(intOut)) {
            return null;
        }
        return category.intOut.eq(intOut);
    }

    private BooleanExpression eqGroupName(String groupName) {
        if (!StringUtils.hasText(groupName)) {
            return null;
        }
        return groupTab.groupName.contains(groupName);
    }

    private BooleanExpression eqCategoriesId(List<Long> categoriesId) {
        if (!StringUtils.hasText(String.valueOf(categoriesId))) {
            return null;
        }
        return groupTab.category.id.in(categoriesId);
    }

    private BooleanExpression eqGroupLocation(String groupLocation) {
        if (!StringUtils.hasText(groupLocation)) {
            return null;
        }
        return groupTab.groupLocation.eq(groupLocation);
    }
}

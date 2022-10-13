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
import team1.togather.domain.groupTab.QGroupTab;

import java.util.List;

import static team1.togather.domain.groupTab.QGroupTab.*;
import static team1.togather.domain.groupTab.QGroupTab.groupTab;


@RequiredArgsConstructor
public class GroupTabRepositoryImpl implements GroupTabRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<GroupTab> MembersGroupTabs(Pageable pageable, String category_first, String category_second, String category_third) {
        List<GroupTab> content = getGroupTabs(pageable, category_first, category_second, category_third);
        JPAQuery<Long> countQuery = getTotal(category_first, category_second, category_third);
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private List<GroupTab> getGroupTabs(Pageable pageable, String category_first, String category_second, String category_third) {
        return jpaQueryFactory.selectFrom(groupTab)
                .where(eqCategoryFirst(category_first),
                        eqCategorySecond(category_second),
                        eqCategoryThird(category_third))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private JPAQuery<Long> getTotal(String category_first, String category_second, String category_third) {
        return jpaQueryFactory
                .select(groupTab.count())
                .from(groupTab)
                .where(eqCategoryFirst(category_first),
                eqCategorySecond(category_second),
                eqCategoryThird(category_third));
    }

    private BooleanExpression eqCategoryFirst(String category_first) {
        if (StringUtils.hasText(category_first)) {
            return null;
        }
        return groupTab.interest.eq(category_first);
    }

    private BooleanExpression eqCategorySecond(String category_second) {
        if (StringUtils.hasText(category_second)) {
            return null;
        }
        return groupTab.interest.eq(category_second);
    }

    private BooleanExpression eqCategoryThird(String category_third) {
        if (StringUtils.hasText(category_third)) {
            return null;
        }
        return groupTab.interest.eq(category_third);
    }
}

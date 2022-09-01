package team1.togather.dto;

import lombok.Data;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.domain.groupTab.GroupTabCategory;
import team1.togather.domain.groupTab.ingrouptab.MemberGrade;
import team1.togather.domain.groupTab.ingrouptab.MemberInGroupTab;
import team1.togather.domain.member.Category;
import team1.togather.domain.member.Member;

import java.time.LocalDateTime;

@Data
public class GroupTabCategoryDto {

    private final Long id;
    private final Long groupTabId;
    private final Long categoryId;

    public static GroupTabCategoryDto of(Long groupTabId, Long categoryId) {
        return new GroupTabCategoryDto(null, groupTabId, categoryId);
    }

    public static GroupTabCategoryDto of(Long id, Long groupTabId, Long categoryId) {
        return new GroupTabCategoryDto(id, groupTabId, categoryId);
    }

    public static GroupTabCategoryDto from(Long groupTabId, Long categoryId) {
        return new GroupTabCategoryDto(
                null,
                groupTabId,
                categoryId
        );
    }

    public static GroupTabCategoryDto from(GroupTabCategory entity) {
        return new GroupTabCategoryDto(
                entity.getId(),
                entity.getGroupTab().getId(),
                entity.getCategory().getId()
        );
    }

    public GroupTabCategory toEntity(GroupTab groupTab, Category category) {
        return GroupTabCategory.of(
                groupTab,
                category
        );
    }
}

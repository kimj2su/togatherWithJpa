package team1.togather.domain.groupTab.ingrouptab;

import lombok.Getter;

public enum MemberGrade {

    GROUP_MASTER("모임장", 0L),
    GROUP_MANAGER("운영진", 1L),
    GROUP_USER("", 2L),
    NOT_GROUP_IN_USER("", 3L);

    @Getter
    private final String description;
    @Getter
    private final Long grade;

    MemberGrade(String description, Long grade) {
        this.description = description;
        this.grade = grade;
    }
}

package team1.togather.dto;

import lombok.Data;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.domain.groupTab.ingrouptab.MemberInGroupTab;
import team1.togather.domain.member.Member;

import java.time.LocalDateTime;

@Data
public class MemberInGroupTabDto {

    private final Long id;
    private final Long groupTabId;
    private final MemberDto memberDto;
    private final Long grade;
    private final LocalDateTime createdAt;
    private final String createdBy;
    private final LocalDateTime modifiedAt;
    private final String modifiedBy;

    public static MemberInGroupTabDto of(Long groupTabId, MemberDto memberDto, Long grade) {
        return new MemberInGroupTabDto(null, groupTabId, memberDto, grade,null, null, null, null);
    }

    public static MemberInGroupTabDto of(Long id, Long groupTabId, MemberDto memberDto, Long grade,  LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new MemberInGroupTabDto(id, groupTabId, memberDto, grade, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static MemberInGroupTabDto from(MemberInGroupTab entity) {
        return new MemberInGroupTabDto(
                entity.getId(),
                entity.getGroupTab().getId(),
                MemberDto.from(entity.getMember()),
                entity.getGrade(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public MemberInGroupTab toEntity(GroupTab groupTab, Member member) {
        return MemberInGroupTab.of(
                groupTab,
                member,
                grade
        );
    }
}

package team1.togather.dto.request;

import lombok.Data;
import team1.togather.domain.groupTab.ingrouptab.MemberGrade;
import team1.togather.dto.MemberDto;
import team1.togather.dto.MemberInGroupTabDto;


@Data
public class  MemberInGroupTabsRequestDto {

    private final Long groupTabId;
    private final Long gradeNumber;

    public static MemberInGroupTabsRequestDto of(Long groupTabId, Long gradeNumber) {
        return new MemberInGroupTabsRequestDto(groupTabId, gradeNumber);
    }

    public MemberInGroupTabDto toDto(Long memberId) {
        MemberGrade grade = null;
        MemberGrade[] values = MemberGrade.values();
        if (gradeNumber == 3) {
            grade = MemberGrade.GROUP_USER;
        } else {
            for (MemberGrade value : values) {
                if (gradeNumber == value.getGrade()) {
                    grade = value;
                    System.out.println("value = " + value);
                }
            }
        }
        return MemberInGroupTabDto.of(
                this.groupTabId,
                MemberDto.from(memberId),
                grade
        );
    }
}

package team1.togather.dto.request;

import lombok.Data;
import team1.togather.dto.MemberDto;
import team1.togather.dto.MemberInGroupTabDto;

import static team1.togather.config.constant.Constant.GROUP_USER;


@Data
public class MemberInGroupTabsRequestDto {

    private final Long groupTabId;
    private final Long grade;

    public static MemberInGroupTabsRequestDto of(Long groupTabId, Long grade) {
        return new MemberInGroupTabsRequestDto(groupTabId, grade);
    }

    public static MemberInGroupTabDto of(Long groupTabId, MemberDto memberDto, Long grade) {
        return new MemberInGroupTabDto(null, groupTabId, memberDto, grade,null, null, null, null);
    }

    public MemberInGroupTabDto toDto(Long memberId) {
        return MemberInGroupTabDto.of(
                this.groupTabId,
                MemberDto.from(memberId),
                GROUP_USER
        );
    }
}

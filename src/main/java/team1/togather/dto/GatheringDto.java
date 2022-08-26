package team1.togather.dto;

import lombok.Data;
import team1.togather.domain.gathring.Gathering;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.domain.member.Member;


@Data
public class GatheringDto {

    private final Long id;

    private final String gaName;
    private final String gaDate;
    private final String time;
    private final String gaPlace;
    private final String price;
    private final int gaLimit;

    private final Long groupTabId;

    private final MemberDto memberDto;

    public static GatheringDto of(Long id, String gaName, String gaDate, String time, String gaPlace, String price, int gaLimit, Long groupTabId, MemberDto memberDto) {
        return new GatheringDto(id, gaName, gaDate, time, gaPlace, price, gaLimit, groupTabId, memberDto);
    }

    public static GatheringDto from(Gathering entity) {
        return new GatheringDto(
                entity.getId(),
                entity.getGaName(),
                entity.getGaDate(),
                entity.getTime(),
                entity.getGaPlace(),
                entity.getPrice(),
                entity.getGaLimit(),
                entity.getGroupTab().getId(),
                MemberDto.from(entity.getMember())
        );
    }

    public Gathering toEntity(GroupTab groupTab, Member member) {
        return Gathering.of(
                null,
                gaName,
                gaDate,
                time,
                gaPlace,
                price,
                gaLimit,
                groupTab,
                member
        );
    }
}

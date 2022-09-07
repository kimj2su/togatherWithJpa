package team1.togather.dto.response;

import lombok.Getter;
import lombok.Setter;
import team1.togather.domain.gathring.Gathering;
import team1.togather.dto.GatheringDto;
import team1.togather.dto.MemberDto;

@Getter
@Setter
public class GatheringsResponseDto {

    private final Long id;
    private final String gaName;
    private final String gaDate;
    private final String time;
    private final String gaPlace;
    private final String price;
    private final int gaLimit;
    private final Long groupTabId;
    private final MemberDto memberDto;

    public GatheringsResponseDto(Long id, String gaName, String gaDate, String time, String gaPlace, String price, int gaLimit, Long groupTabId, MemberDto memberDto) {
        this.id = id;
        this.gaName = gaName;
        this.gaDate = gaDate;
        this.time = time;
        this.gaPlace = gaPlace;
        this.price = price;
        this.gaLimit = gaLimit;
        this.groupTabId = groupTabId;
        this.memberDto = memberDto;
    }

    public static GatheringsResponseDto of(Long id, String gaName, String gaDate, String time, String gaPlace, String price, int gaLimit, Long groupTabId, MemberDto memberDto) {
       return new GatheringsResponseDto(id, gaName, gaDate, time, gaPlace, price, gaLimit, groupTabId, memberDto);
    }

    public static GatheringsResponseDto from(GatheringDto gatheringDto) {
        return new GatheringsResponseDto(
                gatheringDto.getId(),
                gatheringDto.getGaName(),
                gatheringDto.getGaDate(),
                gatheringDto.getTime(),
                gatheringDto.getGaPlace(),
                gatheringDto.getPrice(),
                gatheringDto.getGaLimit(),
                gatheringDto.getGroupTabId(),
                gatheringDto.getMemberDto()
        );
    }
}

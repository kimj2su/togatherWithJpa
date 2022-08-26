package team1.togather.dto.request;

import lombok.Data;
import team1.togather.dto.GatheringDto;
import team1.togather.dto.MemberDto;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class GatheringRequestDto {

    @NotBlank
    private String gaName;

    @NotBlank
    private String gaDate;
    @NotBlank
    private String time;
    @NotBlank
    private String gaPlace;
    @NotBlank
    private String price;
    @NotBlank
    private int gaLimit;
    @NotBlank
    private Long groupTabId;

   public GatheringRequestDto() {}

    public GatheringRequestDto(String gaName, String gaDate, String time, String gaPlace, String price, int gaLimit, Long groupTabId) {
        this.gaName = gaName;
        this.gaDate = gaDate;
        this.time = time;
        this.gaPlace = gaPlace;
        this.price = price;
        this.gaLimit = gaLimit;
        this.groupTabId = groupTabId;
    }

    public static GatheringRequestDto of(String gaName, String gaDate, String time, String gaPlace, String price, int gaLimit, Long groupTabId) {
        return new GatheringRequestDto(gaName, gaDate, time, gaPlace, price, gaLimit, groupTabId);
    }

    public GatheringDto toDto(MemberDto memberDto) {
        return GatheringDto.of(
                null,
                this.gaName,
                this.gaDate,
                this.time,
                this.gaPlace,
                this.price,
                this.gaLimit,
                this.groupTabId,
                memberDto
        );
    }
}

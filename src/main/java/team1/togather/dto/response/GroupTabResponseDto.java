package team1.togather.dto.response;

import lombok.Data;
import team1.togather.domain.groupTab.UploadFile;
import team1.togather.dto.GroupTabDto;

@Data
public class GroupTabResponseDto {

    private final String groupLocation;
    private final String groupName;
    private final String groupIntro;
    private final String interest;
    private final int memberLimit;
    private final UploadFile uploadFile;
    private final String userId;

    public static GroupTabResponseDto from(GroupTabDto dto) {
        return new GroupTabResponseDto(
                dto.getGroupLocation(),
                dto.getGroupName(),
                dto.getGroupIntro(),
                dto.getInterest(),
                dto.getMemberLimit(),
                dto.getUploadFile(),
                dto.getUserId()
        );
    }
}

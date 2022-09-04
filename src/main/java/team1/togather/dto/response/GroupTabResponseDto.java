package team1.togather.dto.response;

import lombok.Getter;
import lombok.Setter;
import team1.togather.domain.groupTab.UploadFile;
import team1.togather.dto.GroupTabDto;
import team1.togather.dto.MemberInGroupTabDto;

import java.util.Set;

@Getter
@Setter
public class GroupTabResponseDto {
    private final Long id;
    private final String groupLocation;
    private final String groupName;
    private final String groupIntro;
    private final String interest;
    private final int memberLimit;
    private final UploadFile uploadFile;
    private final String userId;
    private final Set<MemberInGroupTabDto> memberInGroupTabDto;

    public GroupTabResponseDto(Long id, String groupLocation, String groupName, String groupIntro, String interest, int memberLimit, UploadFile uploadFile, String userId, Set<MemberInGroupTabDto> memberInGroupTabDto) {
        this.id = id;
        this.groupLocation = groupLocation;
        this.groupName = groupName;
        this.groupIntro = groupIntro;
        this.interest = interest;
        this.memberLimit = memberLimit;
        this.uploadFile = uploadFile;
        this.userId = userId;
        this.memberInGroupTabDto = memberInGroupTabDto;
    }

    public static GroupTabResponseDto from(GroupTabDto dto) {
        return new GroupTabResponseDto(
                dto.getId(),
                dto.getGroupLocation(),
                dto.getGroupName(),
                dto.getGroupIntro(),
                dto.getInterest(),
                dto.getMemberLimit(),
                dto.getUploadFile(),
                dto.getMemberDto().getUserId(),
                dto.getMemberInGroupTabDto()
        );
    }
}

package team1.togather.dto.response;

import lombok.Data;
import team1.togather.domain.groupTab.UploadFile;
import team1.togather.dto.GroupTabWithMembersDto;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GroupTabWithMembersResponseDto {

    private final Long id;
    private final String groupLocation;
    private final String groupName;
    private final String groupIntro;
    private final String interest;
    private final int memberLimit;
    private final UploadFile uploadFile;
    private final String userId;
    private final List<MemberInGroupTabResponseDto> memberInGroupTabResponseDtos;

    public static GroupTabWithMembersResponseDto of(Long id, String groupLocation, String groupName, String groupIntro, String interest, int memberLimit, UploadFile uploadFile, String userId, List<MemberInGroupTabResponseDto> memberInGroupTabResponseDtos) {
        return new GroupTabWithMembersResponseDto(id, groupLocation, groupName, groupIntro, interest, memberLimit, uploadFile, userId, memberInGroupTabResponseDtos);
    }

    public static GroupTabWithMembersResponseDto from(GroupTabWithMembersDto membersInGroupTabDto) {
        return new GroupTabWithMembersResponseDto(
                membersInGroupTabDto.getId(),
                membersInGroupTabDto.getGroupLocation(),
                membersInGroupTabDto.getGroupName(),
                membersInGroupTabDto.getGroupIntro(),
                membersInGroupTabDto.getInterest(),
                membersInGroupTabDto.getMemberLimit(),
                membersInGroupTabDto.getUploadFile(),
                membersInGroupTabDto.getMemberDto().getUserId(),
                membersInGroupTabDto.getMemberInGroupTabDto().stream()
                        .map(MemberInGroupTabResponseDto::from)
                        .collect(Collectors.toList())
        );
    }
}

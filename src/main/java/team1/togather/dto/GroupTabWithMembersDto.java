package team1.togather.dto;

import lombok.Data;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.domain.groupTab.UploadFile;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class GroupTabWithMembersDto {

    private final Long id;

    private final String groupLocation;

    private final String groupName;

    private final String groupIntro;

    private final String interest;

    private final int memberLimit;

    private final UploadFile uploadFile;

    private final MemberDto memberDto;

    private final Set<MemberInGroupTabDto> memberInGroupTabDto;
    private final Set<GatheringDto> gatheringDtos;

    private final LocalDateTime createdAt;
    private final String createdBy;
    private final LocalDateTime modifiedAt;
    private final String modifiedBy;

    public static GroupTabWithMembersDto of(Long id, String groupLocation, String groupName, String groupIntro, String interest, int memberLimit, UploadFile uploadFile, MemberDto memberDto, Set<MemberInGroupTabDto> memberInGroupTabDto, Set<GatheringDto> gatheringDtos, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new GroupTabWithMembersDto(id, groupLocation, groupName, groupIntro, interest, memberLimit, uploadFile, memberDto, memberInGroupTabDto, gatheringDtos, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static GroupTabWithMembersDto from(GroupTab entity) {
        return new GroupTabWithMembersDto(
                entity.getId(),
                entity.getGroupLocation(),
                entity.getGroupName(),
                entity.getGroupIntro(),
                entity.getInterest(),
                entity.getMemberLimit(),
                entity.getGroupUploadFile().getAttachFile(),
                MemberDto.from(entity.getMember()),
                entity.getMembersInGroupTab().stream()
                        .map(MemberInGroupTabDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getGathering().stream()
                        .map(GatheringDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}

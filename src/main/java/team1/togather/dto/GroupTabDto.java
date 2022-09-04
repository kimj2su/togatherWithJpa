package team1.togather.dto;

import lombok.Data;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.domain.groupTab.GroupUploadFile;
import team1.togather.domain.groupTab.UploadFile;
import team1.togather.domain.member.Category;
import team1.togather.domain.member.Member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class GroupTabDto {
    private final Long id;
    private final String groupLocation;

    private final String groupName;

    private final String groupIntro;

    private final String interest;

    private final int memberLimit;

    private final UploadFile uploadFile;

    private final MemberDto memberDto;

    private final Set<MemberInGroupTabDto> memberInGroupTabDto;

    private final LocalDateTime createdAt;
    private final String createdBy;
    private final LocalDateTime modifiedAt;
    private final String modifiedBy;

    private GroupUploadFile groupUploadFile;

    public static GroupTabDto of(String groupLocation, String groupName,
                                 String groupIntro, String interest,
                                 int memberLimit, UploadFile uploadFile,
                                 MemberDto memberDto, Set<MemberInGroupTabDto> memberInGroupTabDto) {
        return new GroupTabDto(
                null,
                groupLocation,
                groupName,
                groupIntro,
                interest,
                memberLimit,
                uploadFile,
                memberDto,
                memberInGroupTabDto,
                null,
                null,
                null,
                null
        );
    }

    public static GroupTabDto of(Long id, String groupLocation, String groupName,
                                 String groupIntro, String interest,
                                 int memberLimit, UploadFile uploadFile,
                                 MemberDto memberDto, Set<MemberInGroupTabDto> memberInGroupTabDto, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new GroupTabDto(
                id,
                groupLocation,
                groupName,
                groupIntro,
                interest,
                memberLimit,
                uploadFile,
                memberDto,
                memberInGroupTabDto,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy
        );
    }

    public static GroupTabDto from(GroupTab entity) {
        return new GroupTabDto(
                entity.getId(),
                entity.getGroupLocation(),
                entity.getGroupName(),
                entity.getGroupIntro(),
                entity.getInterest(),
                entity.getMemberLimit(),
                entity.getGroupUploadFile().getAttachFile(),
                MemberDto.from(entity.getMember()),
                null,
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public GroupTab toEntity(Member member, Category category) {
        return GroupTab.of(
                groupLocation,
                groupName,
                groupIntro,
                interest,
                memberLimit,
                toGroupUploadFile(uploadFile),
                member,
                category
        );
    }

    public GroupUploadFile toGroupUploadFile(UploadFile uploadFile) {
        GroupUploadFile groupUploadFile1 = groupUploadFile.of(uploadFile);
        return groupUploadFile1;
    }


}

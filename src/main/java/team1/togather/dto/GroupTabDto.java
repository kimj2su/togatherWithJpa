package team1.togather.dto;

import lombok.Data;
import team1.togather.domain.group.GroupTab;
import team1.togather.domain.group.GroupUploadFile;
import team1.togather.domain.group.UploadFile;
import team1.togather.domain.member.Member;

import java.time.LocalDateTime;

@Data
public class GroupTabDto {

    private final String groupLocation;

    private final String groupName;

    private final String groupIntro;

    private final String interest;

    private final int memberLimit;

    private final UploadFile uploadFile;

    private final Long member_id;
    private final LocalDateTime createdAt;
    private final String createdBy;
    private final LocalDateTime modifiedAt;
    private final String modifiedBy;


    private GroupUploadFile groupUploadFile;




    public static GroupTabDto of(String groupLocation, String groupName,
                                 String groupIntro, String interest,
                                 int memberLimit, UploadFile uploadFile,
                                 Long member_id) {
        return new GroupTabDto(
                groupLocation,
                groupName,
                groupIntro,
                interest,
                memberLimit,
                uploadFile,
                member_id,
                null,
                null,
                null,
                null
        );
    }

    public static GroupTabDto of(String groupLocation, String groupName,
                                 String groupIntro, String interest,
                                 int memberLimit, UploadFile uploadFile,
                                 Long member_id, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new GroupTabDto(
                groupLocation,
                groupName,
                groupIntro,
                interest,
                memberLimit,
                uploadFile,
                member_id,
                createdAt,
                createdBy,
                modifiedAt,
                modifiedBy
        );
    }

    public static GroupTabDto from(GroupTab entity) {
        return new GroupTabDto(
                entity.getGroupLocation(),
                entity.getGroupName(),
                entity.getGroupIntro(),
                entity.getInterest(),
                entity.getMemberLimit(),
                entity.getGroupUploadFile().getAttachFile(),
                entity.getMember().getId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public GroupTab toEntity(Member member) {
        return GroupTab.of(
                groupLocation,
                groupName,
                groupIntro,
                interest,
                memberLimit,
                toGroupUploadFile(uploadFile),
                member
        );
    }

    public GroupUploadFile toGroupUploadFile(UploadFile uploadFile) {
        GroupUploadFile groupUploadFile1 = groupUploadFile.of(uploadFile);
        return groupUploadFile1;
    }


}

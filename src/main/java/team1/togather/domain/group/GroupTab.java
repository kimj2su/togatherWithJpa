package team1.togather.domain.group;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team1.togather.domain.AuditingFields;
import team1.togather.domain.member.Member;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

//@Table(indexes = {
//        @Index(columnList = "email"),
//        @Index(columnList = "nickname", unique = true),
//        @Index(columnList = "phone", unique = true)
//})
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupTab extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupLocation;

    private String groupName;

    private String groupIntro;

    private String interest;

    private int memberLimit;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "group_upload_file_id")
    private GroupUploadFile groupUploadFile;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public GroupTab(String groupLocation, String groupName, String groupIntro, String interest, int memberLimit, GroupUploadFile groupUploadFile, Member member) {
        this.groupLocation = groupLocation;
        this.groupName = groupName;
        this.groupIntro = groupIntro;
        this.interest = interest;
        this.memberLimit = memberLimit;
        this.groupUploadFile = groupUploadFile;
        this.member = member;
    }

    public static GroupTab of(String groupLocation, String groupName, String groupIntro, String interest, int memberLimit, GroupUploadFile groupUploadFile, Member member) {
        return new GroupTab(
            groupLocation,
            groupName,
            groupIntro,
            interest,
                memberLimit,
            groupUploadFile,
            member
        );
    }
}

package team1.togather.domain.groupTab;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team1.togather.domain.AuditingFields;
import team1.togather.domain.groupTab.ingrouptab.MemberInGroupTab;
import team1.togather.domain.member.Member;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    private String userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_upload_file_id")
    private GroupUploadFile groupUploadFile;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "groupTab")
    private List<MemberInGroupTab> membersInGroupTabs = new ArrayList<>();

    public GroupTab(String groupLocation, String groupName, String groupIntro, String interest, int memberLimit, GroupUploadFile groupUploadFile, Member member) {
        this.groupLocation = groupLocation;
        this.groupName = groupName;
        this.groupIntro = groupIntro;
        this.interest = interest;
        this.memberLimit = memberLimit;
        this.groupUploadFile = groupUploadFile;
        this.member = member;
        this.userId = member.getUserId();
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

    public void modifyGroupTabName(String groupName) {
        this.groupName = groupName;
    }

    public void modifyGroupTabLocation(String groupLocation) {
        this.groupLocation = groupLocation;
    }

    public void modifyGroupTabIntro(String groupIntro) {
        this.groupIntro = groupIntro;
    }

    public void modifyGroupTabMemberLimit(int memberLimit) {
        this.memberLimit = memberLimit;
    }

    public void modifyGroupTabUploadFile(GroupUploadFile groupUploadFile) {
        this.groupUploadFile = groupUploadFile;
    }

    public void addMemberInGroupTab(MemberInGroupTab memberInGroupTab) {
        this.membersInGroupTabs.add(memberInGroupTab);
        memberInGroupTab.addGroupTab(this);
    }
}

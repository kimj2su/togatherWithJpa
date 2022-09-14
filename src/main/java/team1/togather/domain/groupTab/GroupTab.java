package team1.togather.domain.groupTab;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import team1.togather.domain.AuditingFields;
import team1.togather.domain.gathring.Gathering;
import team1.togather.domain.groupTab.ingrouptab.ChatRoom;
import team1.togather.domain.groupTab.ingrouptab.MemberInGroupTab;
import team1.togather.domain.member.Category;
import team1.togather.domain.member.Member;

import javax.persistence.*;

import java.util.*;

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
    @Column(name = "group_id")
    private Long id;

    private String groupLocation;

    private String groupName;

    private String groupIntro;

    private String interest;

    private int memberLimit;

    private String userId;

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "group_upload_file_id")
    private GroupUploadFile groupUploadFile;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ToString.Exclude
    @OrderBy("grade asc ")
    @OneToMany(mappedBy = "groupTab", cascade = CascadeType.ALL)
    private final Set<MemberInGroupTab> membersInGroupTab = new LinkedHashSet<>();

    @OneToMany(mappedBy = "groupTab", cascade = CascadeType.ALL )
    private final Set<Gathering> gathering = new LinkedHashSet<>();



    public GroupTab(String groupLocation, String groupName, String groupIntro, String interest, int memberLimit, GroupUploadFile groupUploadFile, Member member, Category category, ChatRoom chatRoom) {
        this.groupLocation = groupLocation;
        this.groupName = groupName;
        this.groupIntro = groupIntro;
        this.interest = interest;
        this.memberLimit = memberLimit;
        this.groupUploadFile = groupUploadFile;
        this.member = member;
        this.userId = member.getUserId();
        this.category = category;
        this.chatRoom = chatRoom;
    }

    public static GroupTab of(String groupLocation, String groupName, String groupIntro, String interest, int memberLimit, GroupUploadFile groupUploadFile, Member member, Category category, ChatRoom chatRoom) {
        return new GroupTab(
                groupLocation,
                groupName,
                groupIntro,
                interest,
                memberLimit,
                groupUploadFile,
                member,
                category,
                chatRoom
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
    public void modifyGroupTabInterest(String interest) {
        this.interest = interest;
    }

    public void modifyGroupTabUploadFile(UploadFile uploadFile) {
        this.groupUploadFile = GroupUploadFile.of( uploadFile);
    }

    public void addMemberInGroupTab(MemberInGroupTab memberInGroupTab) {
        memberInGroupTab.addGroupTab(this);
        membersInGroupTab.add(memberInGroupTab);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupTab)) return false;
        GroupTab groupTab = (GroupTab) o;
        return getId() != null && getId().equals(groupTab.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

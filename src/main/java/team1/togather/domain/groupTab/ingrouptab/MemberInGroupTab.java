package team1.togather.domain.groupTab.ingrouptab;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team1.togather.domain.AuditingFields;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.domain.member.Member;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberInGroupTab extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "group_id")
    private GroupTab groupTab;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private MemberGrade grade;

    private MemberInGroupTab(GroupTab groupTab, Member member, MemberGrade grade) {
        this.groupTab = groupTab;
        this.member = member;
        this.grade = grade;
    }

    public static MemberInGroupTab of(GroupTab groupTab, Member member, MemberGrade grade) {
        return new MemberInGroupTab(groupTab, member, grade);
    }

    public void addGroupTab(GroupTab groupTab) {
        this.groupTab = groupTab;
    }

    public void updateGrade(MemberGrade grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof MemberInGroupTab)) return false;
        MemberInGroupTab that = (MemberInGroupTab) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

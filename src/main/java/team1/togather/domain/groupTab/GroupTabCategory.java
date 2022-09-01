package team1.togather.domain.groupTab;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team1.togather.domain.groupTab.ingrouptab.MemberGrade;
import team1.togather.domain.groupTab.ingrouptab.MemberInGroupTab;
import team1.togather.domain.member.Category;
import team1.togather.domain.member.Member;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class GroupTabCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "group_id")
    private GroupTab groupTab;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private GroupTabCategory(GroupTab groupTab, Category category) {
        this.groupTab = groupTab;
        this.category = category;
    }

    public static GroupTabCategory of(GroupTab groupTab, Category category) {
        return new GroupTabCategory(groupTab, category);
    }

    public void addGroupTab(GroupTab groupTab) {
        this.groupTab = groupTab;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof GroupTabCategory)) return false;
        GroupTabCategory that = (GroupTabCategory) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

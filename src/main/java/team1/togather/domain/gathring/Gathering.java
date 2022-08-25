package team1.togather.domain.gathring;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team1.togather.domain.AuditingFields;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.domain.groupTab.GroupUploadFile;
import team1.togather.domain.member.Member;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gathering{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gaName;
    private String gaDate;
    private String time;
    private String gaPlace;
    private String price;
    private int gaLimit;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "group_id")
    private GroupTab groupTab;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Gathering(Long id, String gaName, String gaDate, String time, String gaPlace, String price, int gaLimit, GroupTab groupTab, Member member) {
        this.id = id;
        this.gaName = gaName;
        this.gaDate = gaDate;
        this.time = time;
        this.gaPlace = gaPlace;
        this.price = price;
        this.gaLimit = gaLimit;
        this.groupTab = groupTab;
        this.member = member;
    }

    public static Gathering of(Long id, String gaName, String gaDate, String time, String gaPlace, String price, int gaLimit, GroupTab groupTab, Member member) {
        return new Gathering(
                id,
                gaName,
                gaDate,
                time,
                gaPlace,
                price,
                gaLimit,
                groupTab,
                member
        );
    }
}

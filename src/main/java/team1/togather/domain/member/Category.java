package team1.togather.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    private String intOut;
    private String intIn;
    private String firstOption;

    public Category(String intOut, String intIn, String firstOption) {
        this.intOut = intOut;
        this.intIn = intIn;
        this.firstOption = firstOption;
    }

    public static Category of(String intOut, String intIn, String firstOption) {
        return new Category(intOut, intIn, firstOption);
    }
}

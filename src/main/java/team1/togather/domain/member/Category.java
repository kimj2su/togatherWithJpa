package team1.togather.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team1.togather.domain.groupTab.GroupTab;

import javax.persistence.*;
import java.util.Objects;

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

    public Category(Long id, String intOut, String intIn, String firstOption) {
        this.id = id;
        this.intOut = intOut;
        this.intIn = intIn;
        this.firstOption = firstOption;
    }

    public static Category of(String intOut, String intIn, String firstOption) {
        return new Category(intOut, intIn, firstOption);
    }

    public static Category of(Long id, String intOut, String intIn, String firstOption) {
        return new Category(id, intOut, intIn, firstOption);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupTab)) return false;
        Category category = (Category) o;
        return getId() != null && getId().equals(category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

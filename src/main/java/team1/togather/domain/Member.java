package team1.togather.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "email", unique = true)
})
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String pwd;

    private String name;

    private String birth;

    private String gender;

    private String phone;

    private String category_first;
    private String category_second;
    private String category_third;

}

package team1.togather.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "email"),
        @Index(columnList = "nickname",unique = true)
})
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String pwd;

    private String username;

    private String nickname;

    private String birth;

    private String gender;

    private String phone;

    private String category_first;
    private String category_second;
    private String category_third;

    public Member(String email, String pwd, String username, String nickname, String birth, String gender, String phone, String category_first, String category_second, String category_third) {
        this.email = email;
        this.pwd = pwd;
        this.username = username;
        this.nickname = nickname;
        this.birth = birth;
        this.gender = gender;
        this.phone = phone;
        this.category_first = category_first;
        this.category_second = category_second;
        this.category_third = category_third;
    }

    public static Member of(String email, String pwd, String username, String nickname, String birth, String gender, String phone, String category_first,
                            String category_second, String category_third) {
        return new Member(
                email,
                pwd,
                username,
                nickname,
                birth,
                gender,
                phone,
                category_first,
                category_second,
                category_third
        );
    }
}

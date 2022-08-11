package team1.togather.domain.member;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Table(indexes = {
        @Index(columnList = "email"),
        @Index(columnList = "nickname", unique = true),
        @Index(columnList = "phone", unique = true)
})
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String pwd;

    private String username;

    private String nickname;

    private String birth;

    private String gender;

    private String phone;

    private String provider;

    private String providerId;

    private String category_first;
    private String category_second;
    private String category_third;

    private LocalDateTime createdDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "member_roles",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> memberRoles = new HashSet<>();

    public Member(String email, String pwd, String username, String nickname, String birth, String gender, String phone, String category_first, String category_second, String category_third, Set<Role> memberRoles) {
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
        this.memberRoles = memberRoles;
        this.createdDate = LocalDateTime.now();
    }

    public static Member of(String email, String pwd, String username, String nickname, String birth, String gender, String phone, String category_first,
                            String category_second, String category_third,Set<Role> memberRoles) {
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
                category_third,
                memberRoles
        );
    }

    @Builder(builderMethodName = "oauth2Builder")
    public Member(String email, String username,
                  Set<Role> memberRoles, String provider, String providerId) {
        this.email = email;
        this.username = username;
        this.createdDate = LocalDateTime.now();
        this.memberRoles = memberRoles;
        this.provider = provider;
        this.providerId = providerId;
    }

    public void oauth2Member(String nickname, String birth, String gender, String category_first, String category_second, String category_third) {
        this.nickname = nickname;
        this.birth = birth;
        this.gender = gender;
        this.category_first = category_first;
        this.category_second = category_second;
        this.category_third = category_third;
    }
}

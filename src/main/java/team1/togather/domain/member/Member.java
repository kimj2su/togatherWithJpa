package team1.togather.domain.member;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Table(indexes = {
        @Index(columnList = "email"),
        @Index(columnList = "userId", unique = true)
})
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String userId;

    private String password;

    private String email;

    private String birth;

    private String gender;

    @Column(name = "provider")
    private String provider;

    @Column(name = "provider_id")
    private String providerId;

    private String category_first;
    private String category_second;
    private String category_third;

    @CreatedDate
    private LocalDateTime createdDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "member_roles",
            joinColumns = {@JoinColumn(name = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @ToString.Exclude
    private Set<Role> memberRoles = new HashSet<>();

    public void setMemberRoles(Set<Role> memberRoles) {
        this.memberRoles = memberRoles;
    }

    public Member(Long id, String username, String userId, String password, String email, String birth, String gender, String category_first, String category_second, String category_third, Set<Role> memberRoles) {
        this.id = id;
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
        this.category_first = category_first;
        this.category_second = category_second;
        this.category_third = category_third;
        this.createdDate = LocalDateTime.now();
        this.memberRoles = memberRoles;
    }

    @Builder
    public Member(String username, String userId, String password, String email, String birth, String gender, String category_first, String category_second, String category_third, Set<Role> memberRoles) {
        this.username = username;
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
        this.category_first = category_first;
        this.category_second = category_second;
        this.category_third = category_third;
        this.memberRoles = memberRoles;
    }

    public static Member of(String username, String userId, String password, String email,  String birth, String gender, String category_first,
                            String category_second, String category_third, Set<Role> memberRoles) {
        return new Member(
                username,
                userId,
                password,
                email,
                birth,
                gender,
                category_first,
                category_second,
                category_third,
                memberRoles
        );
    }

    public static Member of(Long id, String username, String userId, String password, String email,  String birth, String gender, String category_first,
                            String category_second, String category_third, Set<Role> memberRoles) {
        return new Member(
                id,
                username,
                userId,
                password,
                email,
                birth,
                gender,
                category_first,
                category_second,
                category_third,
                memberRoles
        );
    }

    public Member(String username, String userId, String email, String provider, String providerId, Set<Role> memberRoles) {
        this.username = username;
        this.userId = userId;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
        this.memberRoles = memberRoles;
    }

    public static Member oauth2Member(String username, String userId, String email,
                                      String provider, String providerId,Set<Role> memberRoles) {
        return new Member(username, userId, email,
                provider, providerId, memberRoles);
    }

    public void oauth2Member(String userId, String birth, String gender, String category_first, String category_second, String category_third) {
        this.userId = userId;
        this.birth = birth;
        this.gender = gender;
        this.category_first = category_first;
        this.category_second = category_second;
        this.category_third = category_third;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member article = (Member) o;
        return getId() != null && getId().equals(article.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

package team1.togather.domain.member;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
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

    private String provider;

    private String providerId;

    private String category_first;
    private String category_second;
    private String category_third;

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

    @Builder
    public Member(String email, String password, String username, String userId, String birth, String gender, String category_first, String category_second, String category_third, Set<Role> memberRoles) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.userId = userId;
        this.birth = birth;
        this.gender = gender;
        this.category_first = category_first;
        this.category_second = category_second;
        this.category_third = category_third;
        this.memberRoles = memberRoles;
        this.createdDate = LocalDateTime.now();
    }

    public static Member of(String email, String password, String username, String userId, String birth, String gender, String category_first,
                            String category_second, String category_third, Set<Role> memberRoles) {
        return new Member(
                email,
                password,
                username,
                userId,
                birth,
                gender,
                category_first,
                category_second,
                category_third,
                memberRoles
        );
    }

    @Builder(builderMethodName = "oauth2Builder")
    public Member(String username, String userId, String email,
                  Set<Role> memberRoles, String provider, String providerId) {
        this.username = username;
        this.userId = userId;
        this.email = email;
        this.createdDate = LocalDateTime.now();
        this.memberRoles = memberRoles;
        this.provider = provider;
        this.providerId = providerId;
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Member member = (Member) o;
        return id != null && Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

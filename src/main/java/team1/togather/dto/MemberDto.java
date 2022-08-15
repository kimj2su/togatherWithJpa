package team1.togather.dto;

import lombok.Data;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;

import java.util.Set;

@Data
public class MemberDto {

    private String username;

    private String userId;

    private String password;

    private String email;

    private String birth;

    private String gender;

    private String category_first;
    private String category_second;
    private String category_third;
    private Set<Role> memberRoles;

    private String provider;

    private String providerId;

    private Long member_id;

    public MemberDto(String username, String userId, String password, String email, String birth, String gender, String category_first, String category_second, String category_third) {
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
        this.category_first = category_first;
        this.category_second = category_second;
        this.category_third = category_third;
    }

    public MemberDto(String userId, String birth, String gender, String category_first, String category_second, String category_third, Long member_id) {
        this.userId = userId;
        this.birth = birth;
        this.gender = gender;
        this.category_first = category_first;
        this.category_second = category_second;
        this.category_third = category_third;
        this.member_id = member_id;
    }

    public static MemberDto of(String nickname, String birth, String gender, String category_first, String category_second, String category_third, Long member_id) {
        return new MemberDto(nickname, birth, gender, category_first, category_second, category_third, member_id);
    }

    public static MemberDto of(String username, String userId, String password, String email, String birth, String gender, String category_first, String category_second, String category_third) {
        return new MemberDto(username, userId, password, email, birth, gender, category_first, category_second, category_third);
    }

    public static MemberDto from(Member entity) {
        return new MemberDto(
                entity.getUsername(),
                entity.getUserId(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getBirth(),
                entity.getGender(),
                entity.getCategory_first(),
                entity.getCategory_second(),
                entity.getCategory_third()
        );
    }

    public Member toEntity() {
        return Member.of(
                username, userId, password, email, birth, gender,category_first, category_second, category_third, memberRoles
        );
    }

    public Member oauth2toEntity() {
        return Member.oauth2Builder()
                .username(username)
                .email(email)
                .memberRoles(memberRoles)
                .provider(provider)
                .providerId(providerId)
                .build();
    }


    public void hasRoleMember(Set<Role> roles) {
        this.memberRoles = roles;
    }

    public void encodingPassword(String pwd) {
        this.password = password;
    }

}

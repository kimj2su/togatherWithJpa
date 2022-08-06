package team1.togather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import team1.togather.domain.Member;
import team1.togather.domain.Role;

import java.util.HashSet;
import java.util.Set;

@Data
public class MemberDto {

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
    private Set<Role> memberRoles;

    public MemberDto(String email, String pwd, String username, String nickname, String birth, String gender, String phone, String category_first, String category_second, String category_third) {
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

    public static MemberDto of(String email, String pwd, String username, String nickname, String birth, String gender, String phone, String category_first, String category_second, String category_third) {
        return new MemberDto(email, pwd, username, nickname, birth, gender, phone, category_first, category_second, category_third);
    }

    public Member toEntity() {
        return Member.of(
                email, pwd, username, nickname, birth, gender, phone,category_first, category_second, category_third, memberRoles
        );
    }

    public void hasRoleMember(Set<Role> roles) {
        this.memberRoles = roles;
    }

    public void encodingPassword(String pwd) {
        this.pwd = pwd;
    }

}

package team1.togather.dto;

import lombok.Data;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;

import java.util.Set;

@Data
public class MemberDto {

    private Long memberId;

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

    public MemberDto(Long memberId, String username, String userId, String password, String email, String birth, String gender, String category_first, String category_second, String category_third) {
        this.memberId = memberId;
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

    public MemberDto(Long memberId, String userId, String birth, String gender, String category_first, String category_second, String category_third) {
        this.memberId = memberId;
        this.userId = userId;
        this.birth = birth;
        this.gender = gender;
        this.category_first = category_first;
        this.category_second = category_second;
        this.category_third = category_third;
    }

    public static MemberDto of(Long member_id, String userId, String birth, String gender, String category_first, String category_second, String category_third) {
        return new MemberDto(member_id, userId, birth, gender, category_first, category_second, category_third);
    }

    public static MemberDto of(Long memberId, String username, String userId, String password, String email, String birth, String gender, String category_first, String category_second, String category_third) {
        return new MemberDto(memberId, username, userId, password, email, birth, gender, category_first, category_second, category_third);
    }

    public static MemberDto from(Long member_id) {
        return new MemberDto(member_id, null, null, null, null, null, null, null, null, null);
    }

    public static MemberDto from(Member entity) {
        return new MemberDto(
                entity.getId(),
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


    public void hasRoleMember(Set<Role> roles) {
        this.memberRoles = roles;
    }

    public void encodingPassword(String password) {
        this.password = password;
    }

}

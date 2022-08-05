package team1.togather.dto;

import lombok.Data;
import team1.togather.domain.Member;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberDto {

    private final String email;

    private final String pwd;

    private final String username;

    private final String nickname;

    private final String birth;

    private final String gender;

    private final String phone;

    private final String category_first;
    private final String category_second;
    private final String category_third;

    public static MemberDto of(String email, String pwd, String username, String nickname, String birth, String gender, String phone, String category_first,String category_second, String category_third) {
        return new MemberDto(email, pwd, username, nickname, birth, gender, phone, category_first, category_second, category_third);
    }

    public Member toEntity() {
        return Member.of(
                email, pwd, username, nickname, birth, gender, phone,category_first, category_second, category_third
        );
    }
}

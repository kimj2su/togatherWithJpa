package team1.togather.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team1.togather.dto.MemberDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {

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

}

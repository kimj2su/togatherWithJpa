package team1.togather.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {

    private String username;

    private String userId;

    private String email;

    private String birth;

    private String gender;


    private String category_first;
    private String category_second;
    private String category_third;

}

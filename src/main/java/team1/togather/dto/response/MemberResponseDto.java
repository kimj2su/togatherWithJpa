package team1.togather.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team1.togather.domain.member.Role;
import team1.togather.dto.MemberDto;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String username;

    private String userId;

    private String email;

    private String birth;

    private String gender;


    private String category_first;
    private String category_second;
    private String category_third;
    private Set<Role> memberRoles;
    public static MemberResponseDto from(MemberDto dto) {
        return new MemberResponseDto(
                dto.getMemberId(),
                dto.getUsername(),
                dto.getUserId(),
                dto.getEmail(),
                dto.getBirth(),
                dto.getGender(),
                dto.getCategory_first(),
                dto.getCategory_second(),
                dto.getCategory_third(),
                dto.getMemberRoles()
        );
    }

}

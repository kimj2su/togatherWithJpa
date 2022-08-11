package team1.togather.dto.request;

import lombok.Data;
import team1.togather.domain.member.Role;
import team1.togather.dto.MemberDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class MemberOauth2RequestDto {

    @Size(min = 2, max = 6)
    @NotBlank
    private final String nickname;

    @NotEmpty
    private final String birth;

    private final String gender;

    private final String category_first;
    private final String category_second;
    private final String category_third;
    private final Set<Role> userRoles;

    public MemberDto toDto(Long member_id) {
        return MemberDto.of(
                this.nickname,
                this.birth,
                this.gender,
                this.category_first,
                this.category_second,
                this.category_third,
                member_id
        );
    }
}

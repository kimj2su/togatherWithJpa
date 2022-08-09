package team1.togather.dto.request;

import lombok.Data;
import team1.togather.domain.member.Role;
import team1.togather.dto.MemberDto;

import javax.validation.constraints.*;
import java.util.Set;

@Data
public class MemberRequestDto {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String pwd;

    @NotBlank
    private final String username;

    @Size(min = 2, max = 6)
    @NotBlank
    private final String nickname;

    @NotEmpty
    private final String birth;

    private final String gender;

    @NotBlank
//    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private final String phone;

    private final String category_first;
    private final String category_second;
    private final String category_third;
    private final Set<Role> userRoles;

    public MemberDto toDto() {
        return MemberDto.of(
                this.email,
                this.pwd,
                this.username,
                this.nickname,
                this.birth,
                this.gender,
                this.phone,
                this.category_first,
                this.category_second,
                this.category_third
        );
    }
}

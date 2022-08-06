package team1.togather.dto.request;

import lombok.Data;
import team1.togather.domain.Role;
import team1.togather.dto.MemberDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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

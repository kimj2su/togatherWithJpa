package team1.togather.dto.request;

import lombok.Data;
import team1.togather.domain.member.Role;
import team1.togather.dto.MemberDto;

import javax.validation.constraints.*;
import java.util.Set;

@Data
public class MemberRequestDto {

    @NotBlank
    private final String username;

    @Size(min = 2, max = 6)
    @NotBlank
    private final String userId;

    @NotBlank
    private final String password;

    @Email
    @NotBlank
    private final String email;

    @NotEmpty
    private final String birth;

    private final String gender;
//
//    @NotBlank
////    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
//    private final String phone;

    @NotEmpty(message = "관심사는 최소 한개 이상 설정해야됩니다.")
    private final String category_first;
    private final String category_second;
    private final String category_third;
    private final Set<Role> userRoles;

    public MemberDto toDto() {
        return MemberDto.of(
                null,
                this.username,
                this.userId,
                this.password,
                this.email,
                this.birth,
                this.gender,
                this.category_first,
                this.category_second,
                this.category_third
        );
    }
}

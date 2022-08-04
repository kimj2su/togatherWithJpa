package team1.togather.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberRequest {

    @NotEmpty
    private final String email;

    @NotEmpty
    private final String pwd;

    @NotEmpty
    private final String name;

    @NotEmpty
    private final String birth;

    private final String gender;

    @NotEmpty
    private final String phone;
}

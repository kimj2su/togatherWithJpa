package team1.togather.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import team1.togather.domain.group.GroupUploadFile;
import team1.togather.domain.group.UploadFile;
import team1.togather.domain.member.Member;
import team1.togather.dto.GroupTabDto;
import team1.togather.dto.MemberDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class GroupTabRequestDto {

    @NotBlank
    private String groupLocation;
    @NotBlank
    private String groupName;

    private String groupIntro;
    @NotBlank
    private String interest;

    @NotBlank
    @Size(min = 2)
    private int memberLimit;

    @NotBlank
    private MultipartFile attachFile;

    private UploadFile uploadFile;


    public GroupTabDto toDto(Long member_id) {
        return GroupTabDto.of(
                this.groupLocation,
                this.groupName,
                this.groupIntro,
                this.interest,
                this.memberLimit,
                this.uploadFile,
                member_id
        );
    }
}

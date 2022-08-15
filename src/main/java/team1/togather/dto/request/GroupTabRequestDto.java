package team1.togather.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import team1.togather.domain.groupTab.UploadFile;
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

    public GroupTabRequestDto() {
    }

    public GroupTabRequestDto(String groupLocation, String groupName, String groupIntro, String interest, int memberLimit, MultipartFile attachFile) {
        this.groupLocation = groupLocation;
        this.groupName = groupName;
        this.groupIntro = groupIntro;
        this.interest = interest;
        this.memberLimit = memberLimit;
        this.attachFile = attachFile;
    }

    public static GroupTabRequestDto of(String groupLocation, String groupName, String groupIntro, String interest, int memberLimit, MultipartFile attachFile) {
        return new GroupTabRequestDto(groupLocation, groupName, groupIntro, interest, memberLimit, attachFile);
    }

    public GroupTabDto toDto(Member member) {
        return GroupTabDto.of(
                this.groupLocation,
                this.groupName,
                this.groupIntro,
                this.interest,
                this.memberLimit,
                this.uploadFile,
                MemberDto.from(member)
        );
    }
}

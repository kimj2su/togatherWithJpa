package team1.togather.dto.response;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import team1.togather.domain.group.UploadFile;
import team1.togather.dto.GroupTabDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class GroupTabResponseDto {

    private final String groupLocation;
    private final String groupName;
    private final String groupIntro;
    private final String interest;
    private final int memberLimit;
    private final UploadFile uploadFile;
    private final String createBy;

    public static GroupTabResponseDto from(GroupTabDto dto) {
        return new GroupTabResponseDto(
                dto.getGroupLocation(),
                dto.getGroupName(),
                dto.getGroupIntro(),
                dto.getInterest(),
                dto.getMemberLimit(),
                dto.getUploadFile(),
                dto.getCreatedBy()
        );
    }
}

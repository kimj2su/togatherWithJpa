package team1.togather.dto.response;

import lombok.Data;
import team1.togather.dto.MemberDto;
import team1.togather.dto.MemberInGroupTabDto;

import java.time.LocalDateTime;

@Data
public class MemberInGroupTabResponseDto {

//    private final Long id;
    private final String userId;
    private final Long grade;
//    private final LocalDateTime createdAt;

    public static MemberInGroupTabResponseDto of(String userId, Long grade) {
        return new MemberInGroupTabResponseDto(userId, grade);
    }

    public static MemberInGroupTabResponseDto from(MemberInGroupTabDto memberInGroupTabDto) {
        if (memberInGroupTabDto.getMemberDto() == null) {
            return new MemberInGroupTabResponseDto(
                    null,
                    memberInGroupTabDto.getGrade()
            );
        }
        return new MemberInGroupTabResponseDto(
                memberInGroupTabDto.getMemberDto().getUserId(),
                memberInGroupTabDto.getGrade()
        );
    }
}

package team1.togather.dto.response;

import lombok.Data;
import team1.togather.dto.MemberDto;
import team1.togather.dto.MemberInGroupTabDto;

import java.time.LocalDateTime;

@Data
public class MemberInGroupTabResponseDto {

    private final Long id;
    private final String userId;
    private final Long grade;
    private final LocalDateTime createdAt;

    public static MemberInGroupTabResponseDto of(Long id, String userId, Long grade, LocalDateTime createdAt) {
        return new MemberInGroupTabResponseDto( id, userId, grade, createdAt);
    }

    public static MemberInGroupTabResponseDto from(MemberInGroupTabDto memberInGroupTabDto) {
        return new MemberInGroupTabResponseDto(
                memberInGroupTabDto.getId(),
                memberInGroupTabDto.getMemberDto().getUserId(),
                memberInGroupTabDto.getGrade(),
                memberInGroupTabDto.getCreatedAt()
        );
    }
}

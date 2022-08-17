package team1.togather.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.domain.groupTab.GroupUploadFile;
import team1.togather.domain.groupTab.UploadFile;
import team1.togather.domain.groupTab.ingrouptab.MemberInGroupTab;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;
import team1.togather.dto.MemberDto;
import team1.togather.dto.MemberInGroupTabDto;
import team1.togather.repository.GroupTabRepository;
import team1.togather.repository.MemberInGroupTabRepository;
import team1.togather.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 모임 가입")
@ExtendWith(MockitoExtension.class)
class MemberInGroupTabServiceTest {

    @InjectMocks private MemberInGroupTabService sut;

    @Mock
    private GroupTabRepository groupTabRepository;

    @Mock
    private MemberInGroupTabRepository memberInGroupTabRepository;

    @Mock
    private MemberRepository memberRepository;

    @DisplayName("모임 ID로 조회하면, 해당하는 모임에 가입한 멤버 리스트를 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnsArticleComments() {
        // Given
        Long groupTabId = 1L;
        MemberInGroupTab expected = createMemberInGroupTab();
        System.out.println("expected = " + expected);
        given(memberInGroupTabRepository.findByGroupTab_Id(groupTabId)).willReturn(List.of(expected));

        // When
        List<MemberInGroupTabDto> actual = sut.searchMemberInGroupTabs(groupTabId);

        // Then
        assertThat(actual)
                .hasSize(1)
                .first().hasFieldOrPropertyWithValue("grade", expected.getGrade());
        then(memberInGroupTabRepository).should().findByGroupTab_Id(groupTabId);
    }

    @DisplayName("회원정보를 입력하면, 모임에 가입한다.")
    @Test
    void givenArticleCommentInfo_whenSavingArticleComment_thenSavesArticleComment() {
        // Given
        MemberInGroupTabDto memberInGroupTabDto = createMemberInGroupTabDto(1L);
        given(groupTabRepository.getReferenceById(memberInGroupTabDto.getGroupTabId())).willReturn(createGroupTab());
        given(memberRepository.getReferenceById(memberInGroupTabDto.getMemberDto().getMemberId())).willReturn(createNewMember());
        given(memberInGroupTabRepository.save(any(MemberInGroupTab.class))).willReturn(null);

        // When
        sut.saveMemberInGroupTab(memberInGroupTabDto);

        // Then
        then(groupTabRepository).should().getReferenceById(memberInGroupTabDto.getGroupTabId());
        then(memberRepository).should().getReferenceById(memberInGroupTabDto.getMemberDto().getMemberId());
        then(memberInGroupTabRepository).should().save(any(MemberInGroupTab.class));
    }


    private MemberInGroupTabDto createMemberInGroupTabDto(Long memberId) {
        return MemberInGroupTabDto.of(
                memberId,
                1L,
                createNewMemberDto(),
                0L,
                LocalDateTime.now(),
                "jisu",
                LocalDateTime.now(),
                "jisu"
        );
    }

    private MemberDto createNewMemberDto() {
        return MemberDto.of(
                1L,
                "김지수",
                "jisu1",
                "1234",
                "jisu@email.com",
                "2022-08-07",
                "M",
                "category_first",
                "category_second",
                "category_third"
        );
    }

    private GroupTab createGroupTab() {
        return GroupTab.of("서울",
                "테스트 모임",
                "테스트 모임 소개",
                "관심사",
                10,
                createGroupUploadFile(),
                createNewMember());
    }
    private MemberInGroupTab createMemberInGroupTab() {
        return MemberInGroupTab.of(
                GroupTab.of("서울",
                        "테스트 모임",
                        "테스트 모임 소개",
                        "관심사",
                        10,
                        createGroupUploadFile(),
                        createNewMember())
                ,createNewMember()
                ,2L
        );
    }
    private GroupUploadFile createGroupUploadFile() {
        return new GroupUploadFile(createUploadFile());
    }
    private UploadFile createUploadFile() {
        return new UploadFile("테스트 파일 이름", "테스트 서버 저장 파일 이름");
    }

    private Member createNewMember() {
        Member of = Member.of(
                "김지수",
                "jisu1",
                "1234",
                "jisu@email.com",
                "2022-08-07",
                "M",
                "category_first",
                "category_second",
                "category_third",
                roles()
        );
        return of;
    }

    private Set<Role> roles() {
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleName("ROLE_USER");
        role.setRoleDesc("사용자권한");
        roles.add(role);
        return roles;
    }

}
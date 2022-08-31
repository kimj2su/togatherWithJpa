package team1.togather.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.domain.groupTab.GroupUploadFile;
import team1.togather.domain.groupTab.UploadFile;
import team1.togather.domain.groupTab.ingrouptab.MemberGrade;
import team1.togather.domain.groupTab.ingrouptab.MemberInGroupTab;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;
import team1.togather.dto.GroupTabDto;
import team1.togather.dto.MemberDto;
import team1.togather.dto.GroupTabWithMembersDto;
import team1.togather.dto.MemberInGroupTabDto;
import team1.togather.repository.GroupTabRepository;
import team1.togather.repository.MemberRepository;
import team1.togather.repository.RoleRepository;
import team1.togather.security.configs.TestSecurityConfig;
import team1.togather.service.grouptab.GroupTabService;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("그룹 서비스 테스트")
@Import({TestSecurityConfig.class})
@ExtendWith(MockitoExtension.class)
class GroupTabServiceTest {

    @InjectMocks private GroupTabService sut;

    @Mock private GroupTabRepository groupTabRepository;

    @Mock private MemberRepository memberRepository;

    @Mock private RoleRepository roleRepository;

    @DisplayName("그룹이 없으면 예외를 던진다.")
    @Test
    void givenNotFoundGroupTabId_whenSearchingGroupTab_thenThrowsException() {
        //given
        Long groupTabId = 0L;
        given(groupTabRepository.findById(groupTabId)).willReturn(Optional.empty());

        //when
        Throwable t = catchThrowable(() -> sut.getGroupTab(groupTabId));

        //then
        assertThat(t)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("모임이 없습니다 - groupTabId: " + groupTabId);
        then(groupTabRepository).should().findById(groupTabId);
    }

    @DisplayName("그룹 페이지를 반환한다.")
    @Test
    void givenNoCategories_whenSearchingGroupTabs_thenReturnsGroupTabsPage() {
        // Given
        Pageable pageable = Pageable.ofSize(20);
        String category_first = "독서";
        String category_second = "게임";
        String category_third = "개발";
        given(groupTabRepository.MembersGroupTabs(pageable , category_first, category_second, category_third)).willReturn(Page.empty());

        // When
        Page<GroupTabDto> groupTabDtos = sut.MembersGroupTabs(pageable, category_first, category_second, category_third);
        // Then
        assertThat(groupTabDtos).isEmpty();
        then(groupTabRepository).should().MembersGroupTabs(pageable, category_first, category_second, category_third);
    }

    @DisplayName("그룹 페이지를 반환한다.")
    @Test
    void givenNoSearchParameters_whenSearchingGroupTabs_thenReturnsGroupTabsPage() {
        // Given
        Pageable pageable = Pageable.ofSize(20);
        given(groupTabRepository.findAll(pageable)).willReturn(Page.empty());

        // When
        Page<GroupTabDto> groupTabDtos = sut.indexGroupTabs(pageable);

        // Then
        assertThat(groupTabDtos).isEmpty();
        then(groupTabRepository).should().findAll(pageable);
    }

    @DisplayName("그룹을 조회하면, 그룹을 반환한다.")
    @Test
    void givenGroupTabId_whenSearchingGroupTab_thenReturnsGroupTab() {
        // Given
        Long groupTabId = 1L;
        GroupTab groupTab = createGroupTab();
        given(groupTabRepository.findById(groupTabId)).willReturn(Optional.of(groupTab));

        // When
        GroupTabDto dto = sut.getGroupTab(groupTabId);

        // Then
        assertThat(dto)
                .hasFieldOrPropertyWithValue("groupLocation", groupTab.getGroupLocation())
                .hasFieldOrPropertyWithValue("groupName", groupTab.getGroupName())
                .hasFieldOrPropertyWithValue("groupIntro", groupTab.getGroupIntro())
                .hasFieldOrPropertyWithValue("interest", groupTab.getInterest())
                .hasFieldOrPropertyWithValue("memberLimit", groupTab.getMemberLimit())
                .hasFieldOrPropertyWithValue("uploadFile", groupTab.getGroupUploadFile().getAttachFile());
        then(groupTabRepository).should().findById(groupTabId);
    }

    @DisplayName("그룹을 ID로 조회하면, 그룹에 가입된 멤버까지 반환한다.")
    @Test
    void givenGroupTabId_whenSearchingMembersInGroupTab_thenReturnMembersInGroupTabs() {
        //given
        Long groupTabId = 1L;
        GroupTab groupTab = createGroupTab();
        given(groupTabRepository.findById(groupTabId)).willReturn(Optional.of(groupTab));

        //when
        GroupTabWithMembersDto membersInGroupTabDto = sut.getGroupTabWithMembers(groupTabId);

        //then
        assertThat(membersInGroupTabDto)
                .hasFieldOrPropertyWithValue("groupLocation", groupTab.getGroupLocation())
                .hasFieldOrPropertyWithValue("groupName", groupTab.getGroupName())
                .hasFieldOrPropertyWithValue("groupIntro", groupTab.getGroupIntro())
                .hasFieldOrPropertyWithValue("interest", groupTab.getInterest())
                .hasFieldOrPropertyWithValue("memberLimit", groupTab.getMemberLimit())
                .hasFieldOrPropertyWithValue("uploadFile", groupTab.getGroupUploadFile().getAttachFile())
                .hasFieldOrPropertyWithValue("memberInGroupTabDto", groupTab.getMembersInGroupTab());
        then(groupTabRepository).should().findById(groupTabId);
    }

    @DisplayName("그룹에 가입할때 그룹이 없으면, 예외를 던진다.")
    @Test
    void givenNotFoundGroupTabId_whenSearchingMembersInGroupTab_thenThrowsException() {
        //given
        Long groupTabId = 0L;
        given(groupTabRepository.findById(groupTabId)).willReturn(Optional.empty());

        //when
        Throwable t = catchThrowable(() -> sut.getGroupTabWithMembers(groupTabId));

        //then
        assertThat(t)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("모임이 없습니다 - groupTabId: " + groupTabId);
        then(groupTabRepository).should().findById(groupTabId);
    }

    @DisplayName("그룹 정보를 입력하면, 그룹을 생성한다.")
    @Test
    void givenGroupTabInfo_whenSavingGroupTab_thenSavesGroupTab() {
        // Given
        GroupTabDto dto = createGroupTabDto();
        Member newMember = createNewMember();
        GroupTab groupTab = createGroupTab();
        groupTab.addMemberInGroupTab(createMemberInGroupTab());
        given(memberRepository.getReferenceById(dto.getMemberDto().getMemberId())).willReturn(newMember);
        given(groupTabRepository.save(any(GroupTab.class))).willReturn(groupTab);

        // When
        sut.saveGroupTab(dto);

        // Then
        then(memberRepository).should().getReferenceById(dto.getMemberDto().getMemberId());
        then(groupTabRepository).should().save(any(GroupTab.class));
    }

    @DisplayName("모임의 수정 정보를 입력하면, 모임을 수정한다.")
    @Test
    void givenModifiedGroupTabInfo_whenUpdatingGroupTab_thenUpdatesGroupTab() {
        // Given
        GroupTab groupTab = createGroupTab();
        GroupTabDto dto = createGroupTabDto();
        given(groupTabRepository.getReferenceById(dto.getId())).willReturn(groupTab);
        given(memberRepository.getReferenceById(dto.getMemberDto().getMemberId())).willReturn(dto.getMemberDto().toEntity());

        // When
        sut.updateGroupTab(1L, dto);

        // Then
        assertThat(groupTab)
                .hasFieldOrPropertyWithValue("groupLocation", groupTab.getGroupLocation())
                .hasFieldOrPropertyWithValue("groupName", groupTab.getGroupName())
                .hasFieldOrPropertyWithValue("groupIntro", groupTab.getGroupIntro())
                .hasFieldOrPropertyWithValue("interest", groupTab.getInterest())
                .hasFieldOrPropertyWithValue("memberLimit", groupTab.getMemberLimit());
        then(groupTabRepository).should().getReferenceById(1L);
        then(memberRepository).should().getReferenceById(dto.getMemberDto().getMemberId());
    }

    @DisplayName("모임의 ID를 입력하면, 모임을 삭제한다")
    @Test
    void givenGroupTabId_whenDeletingGroupTab_thenDeletesGroupTab() {
        // Given
        Long articleId = 1L;
        String userId = "jisuTest";
        willDoNothing().given(groupTabRepository).deleteByIdAndMember_UserId(articleId, userId);

        // When
        sut.deleteGroupTab(1L, userId);

        // Then
        then(groupTabRepository).should().deleteByIdAndMember_UserId(articleId, userId);
    }



    private GroupTab createGroupTab() {
        GroupTab groupTab = GroupTab.of(
                "서울",
                "테스트 그룹네임",
                "테스트 그룹 소개",
                "테스트 그룹 관심사",
                10,
                createGroupUploadFile(),
                createNewMember()
        );
        ReflectionTestUtils.setField(groupTab, "id", 1L);

        return groupTab;
    }

    private List<MemberInGroupTab> createMemberInGroupTabList() {
        return List.of();
    }

    private GroupUploadFile createGroupUploadFile() {
        return new GroupUploadFile(createUploadFile());
    }

    private GroupTabDto createGroupTabDto() {
        return new GroupTabDto(
                1L,
                "서울",
                "테스트 그룹네임",
                "테스트 그룹 소개",
                "테스트 그룹 관심사",
                10,
                createUploadFile(),
                createMemberDto(),
                createMemberInGroupTabDtoList(),
                null,
                null,
                null,
                null
        );
    }

    private Set<MemberInGroupTabDto> createMemberInGroupTabDtoList() {
        return Set.of();
    }
    private MemberInGroupTab createMemberInGroupTab() {
        return  MemberInGroupTab.of(
                createGroupTab(),
                createNewMember(),
                MemberGrade.GROUP_MASTER
        );
    }

    private MemberDto createMemberDto() {
        return MemberDto.of(
                1L,
                "jisu@email.com",
                "password",
                "jisu",
                "jisu1",
                "2022-08-11",
                "M",
                "category_first",
                "category_second",
                "category_third"
        );
    }

    private UploadFile createUploadFile() {
        return new UploadFile("테스트 파일 이름", "테스트 서버 저장 파일 이름");
    }

    private Member createNewMember() {
        Member of = Member.of(
                "jisu@email.com",
                "password",
                "jisu",
                "jisu1",
                "2022-08-11",
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
        roles.add(findRoleUser());
        return roles;
    }

    private Role findRoleUser() {
        Role roleUser = roleRepository.findByRoleName("ROLE_USER");
        return roleUser;
    }


}
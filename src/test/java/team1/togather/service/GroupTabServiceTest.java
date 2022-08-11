package team1.togather.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;
import team1.togather.domain.group.GroupTab;
import team1.togather.domain.group.GroupUploadFile;
import team1.togather.domain.group.UploadFile;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;
import team1.togather.dto.GroupTabDto;
import team1.togather.repository.GroupTabRepository;
import team1.togather.repository.MemberRepository;
import team1.togather.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("그룹 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class GroupTabServiceTest {

    @InjectMocks private GroupTabService sut;

    @Mock private GroupTabRepository groupTabRepository;

    @Mock private MemberRepository memberRepository;

    @Mock private RoleRepository roleRepository;

    @DisplayName("그룹 정보를 입력하면, 그룹을 생성한다.")
    @Test
    void givenGroupTabInfo_whenSavingGroupTab_thenSavesGroupTab() {
        // Given
        GroupTabDto dto = createGroupTabDto();
        Member newMember = createNewMember();
        GroupTab groupTab = createGroupTab();
        given(memberRepository.getReferenceById(dto.getMember_id())).willReturn(newMember);
        given(groupTabRepository.save(any(GroupTab.class))).willReturn(groupTab);

        // When
        sut.saveGroupTab(dto);

        // Then
        then(memberRepository).should().getReferenceById(dto.getMember_id());
        then(groupTabRepository).should().save(any(GroupTab.class));
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

    private GroupTabDto createGroupTabDto() {
        return new GroupTabDto("서울",
                "테스트 그룹네임",
                "테스트 그룹 소개",
                "테스트 그룹 관심사",
                10,
                createUploadFile(),
                1L,
                null,
                null,
                null,
                null
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
                "nickname",
                "2022-08-11",
                "M",
                "010-3316-3268",
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

    private GroupUploadFile createGroupUploadFile() {
        return new GroupUploadFile(createUploadFile());
    }
}
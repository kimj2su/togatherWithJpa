package team1.togather.service.gathering;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.test.util.ReflectionTestUtils;
import team1.togather.domain.gathring.Gathering;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.domain.groupTab.GroupUploadFile;
import team1.togather.domain.groupTab.UploadFile;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;
import team1.togather.dto.GatheringDto;
import team1.togather.dto.GroupTabDto;
import team1.togather.dto.MemberDto;
import team1.togather.repository.GatheringRepository;
import team1.togather.repository.GroupTabRepository;
import team1.togather.security.configs.TestSecurityConfig;


import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("정모 서비스 테스트")
@Import({TestSecurityConfig.class})
@ExtendWith(MockitoExtension.class)
class GatheringServiceTest {

    @InjectMocks
    private GatheringService sut;

    @Mock
    private GatheringRepository gatheringRepository;
    @Mock
    private GroupTabRepository groupTabRepository;

    @DisplayName("정모 정보를 입력하면, 정모를 생성한다.")
    @Test
    void givenGroupTabInfo_whenSavingGroupTab_thenSavesGroupTab() {
        // Given
        GatheringDto gatheringDto = createGatheringDto();
        Gathering gathering = createGathering();
        given(groupTabRepository.getReferenceById(gatheringDto.getGroupTabId())).willReturn(createGroupTab());
        given(gatheringRepository.save(any(Gathering.class))).willReturn(createGathering());

        // When
        sut.saveGathering(gatheringDto);

        // Then
        then(groupTabRepository).should().getReferenceById(gatheringDto.getGroupTabId());
        then(gatheringRepository).should().save(any(Gathering.class));
    }

    private Gathering createGathering() {
        return Gathering.of(
                1L,
                "테스트 정모",
                "2022-08-25",
                "11-30",
                "서울",
                "30000",
                10,
                createGroupTab(),
                createNewMember()

        );
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
    private UploadFile createUploadFile() {
        return new UploadFile("테스트 파일 이름", "테스트 서버 저장 파일 이름");
    }
    private GatheringDto createGatheringDto() {
        return GatheringDto.of(
                1L,
                "테스트 정모",
                "2022-08-25",
                "11-30",
                "서울",
                "30000",
                10,
                1L,
                createMemberDto()
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

    private Member createNewMember() {
        return Member.of(
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
    }

    private Set<Role> roles() {
        Set<Role> roles = new HashSet<>();
        roles.add(findRoleUser());
        return roles;
    }

    private Role findRoleUser() {
        Role roleUser = new Role();
        roleUser.setId(1L);
        roleUser.setRoleName("ROLE_USER");
        roleUser.setRoleDesc("사용자권한");
        return roleUser;
    }

}
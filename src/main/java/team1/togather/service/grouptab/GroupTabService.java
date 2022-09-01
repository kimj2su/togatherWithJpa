package team1.togather.service.grouptab;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.domain.groupTab.ingrouptab.MemberGrade;
import team1.togather.domain.member.Category;
import team1.togather.domain.member.Member;
import team1.togather.dto.GroupTabCategoryDto;
import team1.togather.dto.GroupTabDto;
import team1.togather.dto.GroupTabWithMembersDto;
import team1.togather.dto.MemberInGroupTabDto;
import team1.togather.repository.CategoryRepository;
import team1.togather.repository.GroupTabRepository;
import team1.togather.repository.MemberRepository;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class GroupTabService {

    private final GroupTabRepository groupTabRepository;

    private final MemberRepository memberRepository;

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<GroupTabDto> MembersGroupTabs(Pageable pageable, String category_first, String category_second, String category_third) {
        return groupTabRepository.MembersGroupTabs(pageable, category_first, category_second, category_third).map(GroupTabDto::from);
    }

    @Transactional(readOnly = true)
    public Page<GroupTabDto> indexGroupTabs(Pageable pageable) {
//       return groupTabRepository.findAll(pageable).map(GroupTabDto::from);
        return groupTabRepository.findByGroupTab(pageable).map(GroupTabDto::from);
    }


    public Long saveGroupTab(GroupTabDto dto) {
        Member member = memberRepository.getReferenceById(dto.getMemberDto().getMemberId());
        GroupTab saveGroupTab = groupTabRepository.save(dto.toEntity(member));
        Category category = categoryRepository.findByCategory(dto.getInterest());
        MemberInGroupTabDto memberInGroupTabDto =  MemberInGroupTabDto.of(saveGroupTab.getId(), dto.getMemberDto(), MemberGrade.GROUP_MASTER);
        saveGroupTab.addMemberInGroupTab(memberInGroupTabDto.toEntity(saveGroupTab, member));
        GroupTabCategoryDto groupTabCategoryDto = GroupTabCategoryDto.of(saveGroupTab.getId(), category.getId());
        saveGroupTab.addGroupTabCategory(groupTabCategoryDto.toEntity(saveGroupTab, category));
        return saveGroupTab.getId();
    }

    @Transactional(readOnly = true)
    public GroupTabDto getGroupTab(Long groupTabId) {
        return groupTabRepository.findById(groupTabId)
                .map(GroupTabDto::from)
                .orElseThrow(() -> new EntityNotFoundException("모임이 없습니다 - groupTabId: " + groupTabId));
    }

    @Transactional(readOnly = true)
    public GroupTabWithMembersDto getGroupTabWithMembers(Long groupTabId) {
        return groupTabRepository.findById(groupTabId)
                .map(GroupTabWithMembersDto::from)
                .orElseThrow(() -> new EntityNotFoundException("모임이 없습니다 - groupTabId: " + groupTabId));
    }

    @Transactional(readOnly = true)
    public Page<GroupTabDto> searchGroupTabs(String searchValue, Pageable pageable) {
        return groupTabRepository.searchGroupTabByInterest(searchValue, pageable).map(GroupTabDto::from);
    }

    public void updateGroupTab(Long groupTabId, GroupTabDto dto) {
        try {
            GroupTab groupTab = groupTabRepository.getReferenceById(groupTabId);
            Member member = memberRepository.getReferenceById(dto.getMemberDto().getMemberId());
            if (groupTab.getMember().equals(member)) {
                if (dto.getGroupLocation() != null) {
                    groupTab.modifyGroupTabLocation(dto.getGroupLocation());
                }
                if (dto.getGroupName() != null) {
                    groupTab.modifyGroupTabName(dto.getGroupName());
                }
                if (dto.getGroupIntro() != null) {
                    groupTab.modifyGroupTabIntro(dto.getGroupIntro());
                }
                if (dto.getMemberLimit() != groupTab.getMemberLimit() && dto.getMemberLimit() != 0) {
                    groupTab.modifyGroupTabMemberLimit(dto.getMemberLimit());
                }
                if (dto.getUploadFile() != null) {
                    groupTab.modifyGroupTabUploadFile(dto.getGroupUploadFile());
                }
            }
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패. 게시글을 수정하는데 필요한 정보를 찾을 수 없습니다. - {}", e.getLocalizedMessage());
        }
    }

    public void deleteGroupTab(Long articleId, String userId) {
        groupTabRepository.deleteByIdAndMember_UserId(articleId, userId);
    }
}

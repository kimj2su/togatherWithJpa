package team1.togather.service.grouptab;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.domain.groupTab.ingrouptab.MemberGrade;
import team1.togather.domain.member.Category;
import team1.togather.domain.member.Member;
import team1.togather.dto.GroupTabDto;
import team1.togather.dto.GroupTabWithMembersDto;
import team1.togather.dto.MemberInGroupTabDto;
import team1.togather.repository.member.CategoryRepository;
import team1.togather.repository.grouptab.GroupTabRepository;
import team1.togather.repository.member.MemberRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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
       return groupTabRepository.findAll(pageable).map(GroupTabDto::from);
    }


    public Long saveGroupTab(GroupTabDto dto) {
        Category category = category(dto);
        Member member = memberRepository.getReferenceById(dto.getMemberDto().getMemberId());
        GroupTab saveGroupTab = groupTabRepository.save(dto.toEntity(member, category));

        MemberInGroupTabDto memberInGroupTabDto =  MemberInGroupTabDto.of(saveGroupTab.getId(), dto.getMemberDto(), MemberGrade.GROUP_MASTER);
        saveGroupTab.addMemberInGroupTab(memberInGroupTabDto.toEntity(saveGroupTab, member));
        return saveGroupTab.getId();
    }


    private Category category(GroupTabDto dto) {
        Category category = categoryRepository.findCategoryByIntIn(dto.getInterest());
        if (category == null) {
            category = categoryRepository.findCategoryByFirstOption(dto.getInterest());
        }
        return category;
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
        if (!StringUtils.hasText(searchValue)) {
            return groupTabRepository.findAll(pageable).map(GroupTabDto::from);
        }
        List<Long> categoryById = categoryRepository.findCategoryByIntOut(searchValue);
        return groupTabRepository.findGroupTabsByCategory_IdIn(categoryById, pageable).map(GroupTabDto::from);
    }

    @Transactional(readOnly = true)
    public Page<GroupTabDto> searchGroupTabsKeyword(String groupName, String intOut, String groupLocation, Pageable pageable) {
        return groupTabRepository.SearchGroupTabs(groupName, intOut, groupLocation, pageable).map(GroupTabDto::from);
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

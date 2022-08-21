package team1.togather.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.domain.groupTab.ingrouptab.MemberInGroupTab;
import team1.togather.domain.member.Member;
import team1.togather.dto.MemberInGroupTabDto;
import team1.togather.repository.GroupTabRepository;
import team1.togather.repository.MemberInGroupTabRepository;
import team1.togather.repository.MemberRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static team1.togather.config.constant.Constant.NOT_GROUP_IN_USER;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MemberInGroupTabService {

    private final GroupTabRepository groupTabRepository;

    private final MemberInGroupTabRepository memberInGroupTabRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberInGroupTabDto searchMemberInGroupTab(Long groupTabId, Long memberId) {
        List<MemberInGroupTab> by = memberInGroupTabRepository.findByGroupTab_IdAndMember_Id(groupTabId, memberId);
        if (by.size() == 0) {
            return MemberInGroupTabDto.from(NOT_GROUP_IN_USER);
        }
        return MemberInGroupTabDto.from(by.get(0));
    }

    @Transactional(readOnly = true)
    public List<MemberInGroupTabDto> searchMemberInGroupTabs(Long groupTabId) {
        return memberInGroupTabRepository.findByGroupTab_Id(groupTabId)
                .stream()
                .map(MemberInGroupTabDto::from).collect(Collectors.toList());
    }

    public void saveMemberInGroupTab(MemberInGroupTabDto memberInGroupTabDto) {
        try {
            GroupTab groupTab = groupTabRepository.getReferenceById(memberInGroupTabDto.getGroupTabId());
            Member member = memberRepository.getReferenceById(memberInGroupTabDto.getMemberDto().getMemberId());
            memberInGroupTabRepository.save(memberInGroupTabDto.toEntity(groupTab, member));
        } catch (EntityNotFoundException e) {
            log.warn("모임 가입 실패. 모임에 가입하기 위한 정보를 찾을 수 없습니다. - {}", e.getLocalizedMessage());
        }
    }

    public void updateMemberInGroupTab(MemberInGroupTabDto memberInGroupTabDto) {
        try {
            MemberInGroupTab memberInGroupTab = memberInGroupTabRepository.getReferenceById(memberInGroupTabDto.getId());
            if (memberInGroupTabDto.getGrade() != null) { memberInGroupTab.updateGrade(memberInGroupTabDto.getGrade()); }
        } catch (EntityNotFoundException e) {
            log.warn("모임 권한 업데이트 실패. 모임을 찾을 수 없습니다 - dto: {}", memberInGroupTabDto);
        }
    }

    public void deleteMemberInGroupTab(Long groupTabId, Long memberId) {
        memberInGroupTabRepository.deleteByGroupTab_IdAndMember_Id(groupTabId, memberId);
    }
}

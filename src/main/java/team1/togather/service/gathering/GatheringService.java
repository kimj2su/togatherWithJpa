package team1.togather.service.gathering;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team1.togather.domain.gathring.Gathering;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.domain.member.Member;
import team1.togather.dto.GatheringDto;
import team1.togather.dto.GroupTabDto;
import team1.togather.repository.gathering.GatheringRepository;
import team1.togather.repository.grouptab.GroupTabRepository;
import team1.togather.repository.member.MemberRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class GatheringService {

    private final GatheringRepository gatheringRepository;
    private final GroupTabRepository groupTabRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<GatheringDto> findAllGathering() {
        return gatheringRepository.findAll().stream().map(GatheringDto::from).collect(Collectors.toList());
    }

    public Long saveGathering(GatheringDto gatheringDto) {
        Member member = memberRepository.getReferenceById(gatheringDto.getMemberDto().getMemberId());
        GroupTab groupTab = groupTabRepository.getReferenceById(gatheringDto.getGroupTabId());
        Gathering gathering = gatheringRepository.save(gatheringDto.toEntity(groupTab, member));
        return gathering.getId();
    }

    public void deleteGathering(Long gatheringId) {
        gatheringRepository.deleteById(gatheringId);
    }

    @Transactional(readOnly = true)
    public GatheringDto getGathering(Long gatheringId) {
        return gatheringRepository.findById(gatheringId)
                .map(GatheringDto::from)
                .orElseThrow(() -> new EntityNotFoundException("정모가 없습니다 - gatheringId: " + gatheringId));
    }
}

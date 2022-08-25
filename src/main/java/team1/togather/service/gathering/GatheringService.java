package team1.togather.service.gathering;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.dto.GatheringDto;
import team1.togather.dto.request.GatheringRequestDto;
import team1.togather.repository.GatheringRepository;
import team1.togather.repository.GroupTabRepository;

@Service
@RequiredArgsConstructor
public class GatheringService {

    private final GatheringRepository gatheringRepository;
    private final GroupTabRepository groupTabRepository;

    public void saveGathering(GatheringDto gatheringDto) {
        GroupTab groupTab = groupTabRepository.getReferenceById(gatheringDto.getGroupTabId());
        gatheringRepository.save(gatheringDto.toEntity(groupTab));
    }
}

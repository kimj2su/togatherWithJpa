package team1.togather.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team1.togather.domain.member.Member;
import team1.togather.dto.GroupTabDto;
import team1.togather.repository.GroupTabRepository;
import team1.togather.repository.MemberRepository;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GroupTabService {

    private final GroupTabRepository groupTabRepository;

    private final MemberRepository memberRepository;



    public Page<GroupTabDto> indexGroupTabs(Pageable pageable) {
       return groupTabRepository.findAll(pageable).map(GroupTabDto::from);
    }
    
    @Transactional
    public void saveGroupTab(GroupTabDto dto) {
        Member member = memberRepository.getReferenceById(dto.getMember_id());
        groupTabRepository.save(dto.toEntity(member));
    }
}

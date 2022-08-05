package team1.togather.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team1.togather.domain.Member;
import team1.togather.dto.MemberDto;
import team1.togather.repository.MemberRepository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void saveMember(MemberDto memberDto) {
        memberRepository.save(memberDto.toEntity());
    }

    public Integer nicknameCheck(String nickname) {
        Integer findByNickname = memberRepository.countByNickname(nickname);
        log.warn("findByNickname error nick = {}" , findByNickname);
        return findByNickname;
    }
}

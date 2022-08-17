package team1.togather.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;
import team1.togather.dto.MemberDto;
import team1.togather.repository.MemberRepository;
import team1.togather.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void saveMember(MemberDto memberDto) {
        Set<Role> roles = new HashSet<>();
        roles.add(findUserRole());
        memberDto.hasRoleMember(roles);
        memberDto.encodingPassword(passwordEncoder.encode(memberDto.getPassword()));
        memberRepository.save(memberDto.toEntity());
    }

    public int userIdCheck(String userId) {
        int countByUserId = memberRepository.countByUserId(userId);
        log.warn("findByNickname count userId = {}" , countByUserId);
        return countByUserId;
    }

    public Role findUserRole() {
        Role userRole = roleRepository.findByRoleName("ROLE_USER");
        return userRole;
    }

    @Transactional
    public void saveOauth2Member(MemberDto toDto) {
        Member member = memberRepository.getReferenceById(toDto.getMemberId());
        member.oauth2Member(
                toDto.getUserId(),
                toDto.getBirth(),
                toDto.getGender(),
                toDto.getCategory_first(),
                toDto.getCategory_second(),
                toDto.getCategory_third()
        );
    }
}

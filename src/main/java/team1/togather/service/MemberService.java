package team1.togather.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team1.togather.domain.Member;
import team1.togather.domain.Role;
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
        memberDto.encodingPassword(passwordEncoder.encode(memberDto.getPwd()));
        memberRepository.save(memberDto.toEntity());
    }

    public Integer nicknameCheck(String nickname) {
        Integer findByNickname = memberRepository.countByNickname(nickname);
        log.warn("findByNickname error nick = {}" , findByNickname);
        return findByNickname;
    }

    public Role findUserRole() {
        Role userRole = roleRepository.findByRoleName("ROLE_USER");
        return userRole;
    }
}

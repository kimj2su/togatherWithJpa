package team1.togather.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team1.togather.domain.groupTab.GroupTab;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;
import team1.togather.dto.MemberDto;
import team1.togather.repository.member.MemberRepository;
import team1.togather.repository.member.RoleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Page<MemberDto> getMembers(Pageable pageable) {
        return memberRepository.findAll(pageable).map(MemberDto::from);
    }

    public MemberDto getMember(Long memberId) {
        return MemberDto.from(memberRepository.findByMember_Id(memberId));
    }

    @Transactional
    public void modifyMember(MemberDto dto, Long memberId) {
        try {
            Member member = memberRepository.findByMember_Id(memberId);
            if (member.getId().equals(memberId)) {
                if (dto.getPassword() != null) {
                    dto.encodingPassword(passwordEncoder.encode(dto.getPassword()));
                    member.modifyPassword(dto.getPassword());
                }
                if (dto.getBirth() != null) {
                    member.modifyBirth(dto.getBirth());
                }
                if (dto.getMemberRoleNames() != null) {
                    Set<Role> roles = new HashSet<>();
                    dto.getMemberRoleNames().forEach(roleName -> {
                        Role role = roleRepository.findByRoleName(roleName);
                        roles.add(role);
                    });
                    member.modifyMemberRoles(roles);
                }
            }
        } catch (EntityNotFoundException e) {
            log.warn("회원 정보 업데이트 실패. - {}", e.getLocalizedMessage());
        }
    }

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

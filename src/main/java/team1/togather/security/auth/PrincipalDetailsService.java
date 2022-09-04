package team1.togather.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team1.togather.domain.member.Member;
import team1.togather.repository.member.MemberRepository;


@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        /** 시큐리티 세션에 유저 정보 저장 **/
        return new PrincipalDetails(memberRepository
                .findByUserId(userId)
                .map(Member.class::cast)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + userId)));

    }
}

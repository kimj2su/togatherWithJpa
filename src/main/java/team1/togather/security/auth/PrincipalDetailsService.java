package team1.togather.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team1.togather.domain.member.Member;
import team1.togather.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> findMember = memberRepository.findByEmailAndProvider(email);
        if (findMember.isEmpty()) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }
        if (findMember != null) {
            return new PrincipalDetails(findMember.get());

        }
        return null;
    }
}

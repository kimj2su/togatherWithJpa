package team1.togather.security.auth.oauth2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;
import team1.togather.repository.member.MemberRepository;
import team1.togather.repository.member.RoleRepository;
import team1.togather.security.auth.PrincipalDetails;
import team1.togather.security.auth.oauth2.provider.FacebookUserInfo;
import team1.togather.security.auth.oauth2.provider.GoogleUserInfo;
import team1.togather.security.auth.oauth2.provider.NaverUserInfo;
import team1.togather.security.auth.oauth2.provider.OAuth2UserInfo;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {


    private final MemberRepository memberRepository;

    private final RoleRepository roleRepository;


    //구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
    //함수 종료시 @AuthenticationPrincipal 어노테이션 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        // 구글로그인 버튼 클릭 -> 구글 로그인창 -> 로그인을 완료 -> code를 리넡(OAuth-Client라이브러리) -> AccecssToken요청
        // userRequest정보 -> loadUser함수 호출() -> 구글로 부터 회원 프로필을 받을 수 있음

        //회원가입을 강제로 진행
        OAuth2UserInfo oAuth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            log.info("구글로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }  else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            log.info("페이스북 로그인 요청");
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            log.info("네이버 로그인 요청");
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        }else {
            log.info("우리는 구글과 페이스북,네이버만 지원해요.");
        }

        Member oauthMember = createOauthMember(oAuth2UserInfo);


        return new PrincipalDetails(oauthMember, oAuth2User.getAttributes());
    }

    private Member createOauthMember(OAuth2UserInfo oAuth2UserInfo) {
        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider+"_"+providerId; //username = google_107438726388440656699
        String email = oAuth2UserInfo.getEmail();

        Set<Role> roles = new HashSet<>();
        roles.add(findRoleUser());

        Member member = memberRepository.findByUsername(username);

        if (member == null) {
            log.info("OAuth 로그인이 최초입니다.");
            member =  Member.oauth2Member(
                    username,
                    username,
                    email,
                    provider,
                    providerId,
                    roles
            );
            memberRepository.save(member);
        } else {
            log.info("로그인을 이미 한적이 있습니다. 당신은 자동회원가입이 되어 있습니다.");
        }
        return member;
    }

    private Role findRoleUser() {
        Role roleUser = roleRepository.findByRoleName("ROLE_USER");
        return roleUser;
    }

}

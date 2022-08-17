package team1.togather.security.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import team1.togather.domain.member.Member;
import team1.togather.domain.member.Role;
import team1.togather.dto.MemberDto;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class PrincipalDetails implements UserDetails, OAuth2User {

    private  Member member;
    private Map<String,Object> attributes;

    public PrincipalDetails(Member member) {
        this.member = member;
    }

    public PrincipalDetails(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return
        member.getMemberRoles()
                .stream()
                .map(Role::getRoleName)
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //OAuth2User 구현 메소드
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return  (String) attributes.get("sub");
    }

    public MemberDto toDto() {
        return MemberDto.of(
                member.getId(),
                member.getEmail(),
                member.getPassword(),
                member.getUsername(),
                member.getUserId(),
                member.getBirth(),
                member.getGender(),
                member.getCategory_first(),
                member.getCategory_second(),
                member.getCategory_third()
        );
    }

    public static PrincipalDetails of(Member member) {
        return new PrincipalDetails(member);
    }

    public static PrincipalDetails from(MemberDto memberDto) {
        return PrincipalDetails.of(
                memberDto.toEntity()
        );
    }
}

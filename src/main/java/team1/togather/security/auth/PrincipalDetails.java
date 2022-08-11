package team1.togather.security.auth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import team1.togather.domain.member.Member;
import team1.togather.dto.MemberDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Data
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
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getMemberRoles().toString();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return member.getPwd();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
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

    public MemberDto toDto(Member member) {
        return MemberDto.of(
                member.getEmail(),
                member.getPwd(),
                member.getUsername(),
                member.getNickname(),
                member.getBirth(),
                member.getGender(),
                member.getPhone(),
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

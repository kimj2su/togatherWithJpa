package team1.togather.security.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import team1.togather.domain.member.Member;
import team1.togather.security.auth.PrincipalDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class Oauth2LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PrincipalDetails userDetails = (PrincipalDetails) principal;
        Member member = userDetails.getMember();

        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행 {}", requestURI);

        if (member.getNickname() != null ) {
            log.info("미인증 사용자 요청");
            //로그인으로 redirect
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}

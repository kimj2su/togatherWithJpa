package team1.togather.controller.login;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team1.togather.domain.member.Member;
import team1.togather.security.auth.PrincipalDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class LoginController {

	@RequestMapping(value="/login")
	public String login(@RequestParam(value = "error", required = false) String error,
						@RequestParam(value = "exception", required = false) String exception, Model model){
		model.addAttribute("error",error);
		model.addAttribute("exception",exception);
		return "login/loginForm";
	}

	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null){
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		return "redirect:/login";
	}

	@GetMapping(value="/denied")
	public String accessDenied(@RequestParam(value = "exception", required = false) String exception, Principal principal, Model model)  {

		Member member = null;

		if (principal instanceof UsernamePasswordAuthenticationToken) {
			PrincipalDetails principalDetails = (PrincipalDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
			member = principalDetails.getMember();
		}

		model.addAttribute("username", member.getUserId());
		model.addAttribute("exception", exception);

		return "user/login/denied";
	}

	@GetMapping("/loginForm")
	public String loginForm(@RequestParam(value = "error", required = false) String error,
							@RequestParam(value = "exception", required = false) String exception, Model model) {
		model.addAttribute("error",error);
		model.addAttribute("exception",exception);
		return "login/loginForm";
	}
}

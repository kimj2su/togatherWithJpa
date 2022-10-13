package team1.togather.controller.user;//package team1.togather.controller.user;
//
//
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import team1.togather.domain.dto.AccountDto;
//import team1.togather.domain.entity.Account;
//import team1.togather.repository.member.RoleRepository;
//import team1.togather.service.UserService;
//
//import java.security.Principal;
//
//@Controller
//@RequiredArgsConstructor
//public class UserController {
//
//	private final UserService userService;
//
//	private final PasswordEncoder passwordEncoder;
//
//	private final RoleRepository roleRepository;
//
//	@GetMapping(value="/users")
//	public String createUser() throws Exception {
//
//		return "user/login/register";
//	}
//
//	@PostMapping(value="/users")
//	public String createUser(AccountDto accountDto) throws Exception {
//
//		ModelMapper modelMapper = new ModelMapper();
//		Account account = modelMapper.map(accountDto, Account.class);
//		account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
//
//		userService.createUser(account);
//
//		return "redirect:/";
//	}
//
//	@GetMapping(value="/mypage")
//	public String myPage(@AuthenticationPrincipal Account account, Authentication authentication, Principal principal) throws Exception {
//
//
//		return "user/mypage";
//	}
//}

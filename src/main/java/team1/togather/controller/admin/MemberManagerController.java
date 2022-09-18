package team1.togather.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import team1.togather.domain.member.Role;
import team1.togather.dto.request.MemberRequestDto;
import team1.togather.dto.response.MemberResponseDto;
import team1.togather.service.member.MemberService;
import team1.togather.service.member.RoleService;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
public class MemberManagerController {

	private final MemberService memberService;
	private final RoleService roleService;

	@GetMapping(value="/admin/members")
	public String getUsers(Model model, @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {

		Page<MemberResponseDto> members = memberService.getMembers(pageable).map(MemberResponseDto::from);
		model.addAttribute("members", members);

		return "admin/members/list";
	}

	@GetMapping(value = "/admin/members/{memberId}")
	public String getUser(@PathVariable(value = "memberId") Long memberId, Model model) {
		MemberResponseDto memberResponseDto = MemberResponseDto.from(memberService.getMember(memberId));
		List<String> roles = memberResponseDto.getMemberRoles().stream().map(Role::getRoleName).collect(Collectors.toList());
		List<Role> roleList = roleService.getRoles();

		model.addAttribute("member", memberResponseDto);
		model.addAttribute("roleList", roleList);
		model.addAttribute("roles", roles);
		return "admin/members/detail";
	}

	@PostMapping(value="/admin/members")
	public String modifyUser(MemberRequestDto memberRequestDto, Long memberId) {
		memberService.modifyMember(memberRequestDto.toDto2(), memberId);

		return "redirect:/admin/members";
	}
}

package team1.togather.controller.admin;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import team1.togather.domain.dto.RoleDto;
import team1.togather.domain.member.Role;
import team1.togather.service.member.RoleService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RoleController {

	private final RoleService roleService;

	@GetMapping(value="/admin/roles")
	public String getRoles(Model model) throws Exception {

		List<Role> roles = roleService.getRoles();
		model.addAttribute("roles", roles);

		return "admin/role/list";
	}

	@GetMapping(value="/admin/roles/register")
	public String viewRoles(Model model) throws Exception {

		RoleDto role = new RoleDto();
		model.addAttribute("role", role);

		return "admin/role/detail";
	}

	@PostMapping(value="/admin/roles")
	public String createRole(RoleDto roleDto) throws Exception {

		ModelMapper modelMapper = new ModelMapper();
		Role role = modelMapper.map(roleDto, Role.class);
		roleService.createRole(role);

		return "redirect:/admin/roles";
	}

	@GetMapping(value="/admin/roles/{id}")
	public String getRole(@PathVariable String id, Model model) throws Exception {

		Role role = roleService.getRole(Long.valueOf(id));

		ModelMapper modelMapper = new ModelMapper();
		RoleDto roleDto = modelMapper.map(role, RoleDto.class);
		model.addAttribute("role", roleDto);

		return "admin/role/detail";
	}

	@GetMapping(value="/admin/roles/delete/{id}")
	public String removeResources(@PathVariable String id, Model model) throws Exception {

		Role role = roleService.getRole(Long.valueOf(id));
		roleService.deleteRole(Long.valueOf(id));

		return "redirect:/admin/resources";
	}
}

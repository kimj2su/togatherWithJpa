package team1.togather.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import team1.togather.config.file.FileStore;
import team1.togather.dto.response.GroupTabResponseDto;
import team1.togather.service.GroupTabService;
import team1.togather.service.PaginationService;

import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

	private final GroupTabService groupTabService;
	private final PaginationService paginationService;
	private final FileStore fileStore;
	@GetMapping("/")
	public String index(
			@PageableDefault(size = 6, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
			Model model
	) {
		Page<GroupTabResponseDto> groupTabs = groupTabService.indexGroupTabs(pageable).map(GroupTabResponseDto::from);
		List<Integer> paginationBarNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), groupTabs.getTotalPages());
		model.addAttribute("groupTabs", groupTabs);
		model.addAttribute("paginationBarNumbers", paginationBarNumbers);

		return "index";
	}

	@ResponseBody
	@GetMapping("/images/{filename}")
	public Resource downloadImage(@PathVariable String filename) throws
			MalformedURLException {
		return new UrlResource("file:" + fileStore.getFullPath(filename));
	}

}

package team1.togather.controller.grouptab;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import team1.togather.dto.response.GroupTabResponseDto;
import team1.togather.service.PaginationService;
import team1.togather.service.grouptab.GroupTabService;
import team1.togather.service.member.CategoryService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class SearchGroupTabsController {

    private final GroupTabService groupTabService;
    private final PaginationService paginationService;
    private final CategoryService categoryService;

    @GetMapping("/search")
    public String searchCategory(
            @PageableDefault(size = 9, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            String searchValue, Model model
    ) {
        Page<GroupTabResponseDto> groupTabs = groupTabService.searchGroupTabs(searchValue, pageable).map(GroupTabResponseDto::from);
        List<Integer> paginationBarNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), groupTabs.getTotalPages());
        List<String> intOut = categoryService.getIntOut();
        model.addAttribute("groupTabs", groupTabs);
        model.addAttribute("paginationBarNumbers", paginationBarNumbers);
        model.addAttribute("intOut", intOut);
        return "groupTabs/search-grouptab";
    }

    @GetMapping("/search-keyword")
    public String searchKeyword(
            @PageableDefault(size = 9, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model, String groupName, String intOut, String groupLocation
    ) {
        Page<GroupTabResponseDto> groupTabs = groupTabService.searchGroupTabsKeyword(groupName, intOut, groupLocation, pageable).map(GroupTabResponseDto::from);
        List<Integer> paginationBarNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), groupTabs.getTotalPages());
        List<String> intOuts = categoryService.getIntOut();
        model.addAttribute("groupTabs", groupTabs);
        model.addAttribute("paginationBarNumbers", paginationBarNumbers);
        model.addAttribute("intOut", intOuts);
        return "groupTabs/search-grouptab";
    }
}

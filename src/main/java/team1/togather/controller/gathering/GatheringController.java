package team1.togather.controller.gathering;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import team1.togather.dto.request.GatheringRequestDto;
import team1.togather.dto.response.GatheringsResponseDto;
import team1.togather.security.auth.PrincipalDetails;
import team1.togather.service.gathering.GatheringService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/gatherings")
public class GatheringController {

    private final GatheringService gatheringService;

    @GetMapping("/new/{groupTabId}")
    public String createForm(@PathVariable Long groupTabId, Model model) {
        model.addAttribute("gathering", new GatheringRequestDto());
        model.addAttribute("groupTabId", groupTabId);
        return "gatherings/createGatheringForm";
    }

    @PostMapping("/new")
    public String saveGathering(Long groupTabId, GatheringRequestDto gatheringRequestDto, @AuthenticationPrincipal PrincipalDetails principalDetails, RedirectAttributes redirectAttributes) {
        gatheringRequestDto.setGroupTabId(groupTabId);
        Long gatheringId = gatheringService.saveGathering(gatheringRequestDto.toDto(principalDetails.toDto()));
        redirectAttributes.addAttribute("gatheringId", gatheringId);
        return "redirect:/gatherings/{gatheringId}";
    }

    @GetMapping("{gatheringId}")
    public String Gathering(@PathVariable Long gatheringId, Model model) {
        GatheringsResponseDto gathering = GatheringsResponseDto.from(gatheringService.getGathering(gatheringId));
        model.addAttribute("gathering", gathering);
        return "gatherings/detail";
    }

    @GetMapping("/gatheringSearchMap")
    public String gatheringSearchMap(String place, Model model) {
        model.addAttribute("place", place);
        return "gatherings/searchMap";
    }
}

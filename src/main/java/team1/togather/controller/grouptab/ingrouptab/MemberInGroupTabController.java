package team1.togather.controller.grouptab.ingrouptab;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team1.togather.dto.request.MemberInGroupTabsRequestDto;
import team1.togather.security.auth.PrincipalDetails;
import team1.togather.service.grouptab.MemberInGroupTabService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mig")
public class MemberInGroupTabController {

    private final MemberInGroupTabService memberInGroupTabService;

    @PostMapping("/new/{groupTabId}")
    public Long newMemberInGroupTabs(MemberInGroupTabsRequestDto dto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        memberInGroupTabService.saveMemberInGroupTab(dto.toDto(principalDetails.getMember().getId()));
        return dto.getGroupTabId();
    }

    @PostMapping("/delete/{groupTabId}")
    public Long deleteMemberInGroupTabs(MemberInGroupTabsRequestDto dto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        memberInGroupTabService.deleteMemberInGroupTab(dto.getGroupTabId(), principalDetails.getMember().getId());
        return dto.getGroupTabId();
    }
}

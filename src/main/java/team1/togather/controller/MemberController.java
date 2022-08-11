package team1.togather.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team1.togather.dto.request.MemberOauth2RequestDto;
import team1.togather.dto.request.MemberRequestDto;
import team1.togather.dto.response.MemberResponseDto;
import team1.togather.security.auth.PrincipalDetails;
import team1.togather.service.MemberService;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("member", new MemberResponseDto());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Validated @ModelAttribute("member") MemberRequestDto memberRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "members/createMemberForm";
        }

        memberService.saveMember(memberRequest.toDto());
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/members/nicknameCheck")
    public Integer nicknameCheck(String nickname) {
        Integer checkedNickname = memberService.nicknameCheck(nickname);
        return checkedNickname;
    }

    @GetMapping("/members/new/oauth2")
    public String createOauth2Form(Model model) {
        model.addAttribute("member", new MemberResponseDto());
        return "members/createOauth2MemberForm";
    }

    @PostMapping("/members/new/oauth2")
    public String createOauth2(@Validated @ModelAttribute("member") MemberOauth2RequestDto memberOauth2RequestDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "members/createOauth2MemberForm";
        }
        memberService.saveOauth2Member(memberOauth2RequestDto.toDto(principalDetails.getMember().getId()));
        return "redirect:/";
    }
}

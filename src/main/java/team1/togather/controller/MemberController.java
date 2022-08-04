package team1.togather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import team1.togather.dto.request.MemberRequest;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/members/new")
    public String createForm(Model model) {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberRequest memberRequest) {
        System.out.println("memberRequest = " + memberRequest);
        return "members/createMemberForm";
    }
}

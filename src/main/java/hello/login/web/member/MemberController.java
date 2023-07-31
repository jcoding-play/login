package hello.login.web.member;

import hello.login.domain.member.Member;
import hello.login.service.member.MemberService;
import hello.login.web.member.form.MemberSaveForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private Logger log = LoggerFactory.getLogger(getClass());

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") MemberSaveForm form) {
        return "signup";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("member") MemberSaveForm form) {
        String name = form.getUsername();
        String loginId = form.getLoginId();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            log.info("비밀번호가 일치하지 않습니다.");
            return "signup";
        }

        Member member = new Member(name, loginId, password);
        memberService.join(member);

        return "redirect:/login";
    }
}

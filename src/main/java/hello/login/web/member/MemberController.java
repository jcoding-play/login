package hello.login.web.member;

import hello.login.domain.member.Member;
import hello.login.service.member.MemberService;
import hello.login.web.member.form.MemberSaveForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {
    private static final int MIN_LENGTH_OF_NAME = 1;
    private static final int MAX_LENGTH_OF_NAME = 10;
    private static final int MAX_LENGTH_OF_LOGIN_ID = 10;
    private static final int MAX_LENGTH_OF_PASSWORD = 10;

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
    public String save(@Validated @ModelAttribute("member") MemberSaveForm form, BindingResult bindingResult) {
        String name = form.getUsername();
        String loginId = form.getLoginId();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            bindingResult.reject("inconsistentPassword", "비밀번호가 일치하지 않습니다.");
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "signup";
        }

        Member member = new Member(name, loginId, password);
        try {
            memberService.join(member);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            bindingResult.reject("duplicate", new Object[]{loginId}, "이미 이용중인 아이디입니다.");
            log.info("errors={}", bindingResult);
            return "signup";
        }
    }
}

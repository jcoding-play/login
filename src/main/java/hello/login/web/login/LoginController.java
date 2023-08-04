package hello.login.web.login;

import hello.login.domain.member.Member;
import hello.login.service.login.LoginService;
import hello.login.web.login.form.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private final LoginService loginService;
    private Logger log = LoggerFactory.getLogger(getClass());

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm form) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm form) {
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("loginMember={}", loginMember);

        if (loginMember == null) {
            return "login";
        }
        return "success";
    }
}

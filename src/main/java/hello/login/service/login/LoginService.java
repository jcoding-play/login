package hello.login.service.login;

import hello.login.domain.member.Member;
import hello.login.service.member.MemberService;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final MemberService memberService;

    public LoginService(MemberService memberService) {
        this.memberService = memberService;
    }

    public Member login(String loginId, String password) {
        return memberService.findMember(loginId)
                .filter(member -> member.isPasswordMatch(password))
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
    }
}

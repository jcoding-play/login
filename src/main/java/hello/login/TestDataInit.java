package hello.login;

import hello.login.domain.member.Member;
import hello.login.service.member.MemberService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestDataInit {

    private final MemberService memberService;

    public TestDataInit(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostConstruct
    public void initMembers() {
        memberService.join(new Member("홍길동", "test", "test!"));
        memberService.join(new Member("이순신", "hello", "hello!"));
    }
}

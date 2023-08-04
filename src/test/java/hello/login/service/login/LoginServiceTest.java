package hello.login.service.login;

import hello.login.domain.member.Member;
import hello.login.repository.MemoryMemberRepository;
import hello.login.service.member.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LoginServiceTest {

    private LoginService loginService;
    private MemberService memberService;
    private MemoryMemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        loginService = new LoginService(memberService);
    }

    @AfterEach
    void tearDown() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("입력된 로그인 아이디와 비밀번호가 일치하는 회원을 찾을 수 있다.")
    void loginSuccess() {
        Member firstMember = new Member("first", "userA", "test!");
        Member secondMember = new Member("second", "userB", "test!");
        memberRepository.save(firstMember);
        memberRepository.save(secondMember);

        Member loginMember = loginService.login("userB", "test!");

        assertThat(loginMember).isSameAs(secondMember);
    }

    @Test
    @DisplayName("입력된 로그인 아이디와 비밀번호가 일치하는 회원이 없다면 null을 반환한다.")
    void loginFail() {
        Member firstMember = new Member("first", "userA", "test!");
        memberRepository.save(firstMember);

        Member loginMember = loginService.login("userA", "test");
        assertThat(loginMember).isNull();
    }
}
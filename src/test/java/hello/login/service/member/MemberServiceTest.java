package hello.login.service.member;

import hello.login.domain.member.Member;
import hello.login.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest {
    private MemberService memberService;
    private MemoryMemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void tearDown() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("회원을 가입할 수 있다.")
    void joinTest() {
        Member member = new Member("userA", "test", "test!");
        memberService.join(member);

        List<Member> members = memberService.findMembers();

        assertThat(members).containsExactly(member);
    }

    @Test
    @DisplayName("중복된 아이디로 회원을 가입하면 예외가 발생한다.")
    void duplicateLoginIdTest() {
        Member member = new Member("userA", "test", "test!");
        memberService.join(member);

        assertThatThrownBy(() -> memberService.join(new Member("userB", "test", "password")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미 이용중인 아이디입니다.");
    }
}

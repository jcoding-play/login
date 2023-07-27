package hello.login.repository;

import hello.login.domain.member.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    private MemoryMemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository = new MemoryMemberRepository();
    }

    @AfterEach
    void tearDown() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("회원을 저장할 수 있다.")
    void saveTest() {
        Member member = new Member("userA", "test", "test!");
        Member savedMember = memberRepository.save(member);

        assertThat(member).isSameAs(savedMember);
    }

    @Test
    @DisplayName("로그인 아이디를 통해 일치하는 회원을 찾을 수 있다.")
    void findByLoginIdTest() {
        Member firstMember = new Member("first", "userA", "test!");
        Member secondMember = new Member("second", "userB", "test!");
        memberRepository.save(firstMember);
        memberRepository.save(secondMember);

        Optional<Member> findMemberOptional = memberRepository.findByLoginId("userB");

        assertThat(findMemberOptional.get()).isSameAs(secondMember);
    }

    @Test
    @DisplayName("저장된 회원 전부를 찾을 수 있다.")
    void findAllTest() {
        Member firstMember = new Member("first", "userA", "test!");
        Member secondMember = new Member("second", "userB", "test!");
        memberRepository.save(firstMember);
        memberRepository.save(secondMember);

        List<Member> members = memberRepository.findAll();

        assertThat(members).containsExactly(firstMember, secondMember);
    }
}
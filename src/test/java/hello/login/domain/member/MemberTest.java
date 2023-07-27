package hello.login.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

public class MemberTest {

    @Test
    @DisplayName("회원은 이름, 아이디, 비밀번호 정보를 가진다.")
    void createMember() {
        Member member = new Member("userA", "test", "test!");
        assertThat(member).isEqualTo(new Member("userA", "test", "test!"));
    }

    @ParameterizedTest
    @CsvSource(value = {"test, true", "spring, false"})
    @DisplayName("회원의 로그인 아이디와 입력된 로그인 아이디가 일치하는지 알 수 있다.")
    void isLoginIdMatchTest(String loginId, boolean expected) {
        Member member = new Member("userA", "test", "test!");

        boolean actual = member.isLoginIdMatch(loginId);

        assertThat(actual).isEqualTo(expected);
    }
}

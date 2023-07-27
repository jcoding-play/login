package hello.login.domain.member;

import java.util.Map;
import java.util.Objects;

public class Member {

    private final String name;
    private final String loginId;
    private final String password;
    private Long id;

    public Member(String name, String loginId, String password) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }

    public void save(Map<Long, Member> store, long id) {
        this.id = id;
        store.put(id, this);
    }

    public boolean isLoginIdMatch(String loginId) {
        return this.loginId.equals(loginId);
    }

    public boolean isPasswordMatch(String password) {
        return this.password.equals(password);
    }

    public boolean isDuplicatedLoginId(Member member) {
        return member.isLoginIdMatch(loginId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(name, member.name) && Objects.equals(loginId, member.loginId) && Objects.equals(password, member.password) && Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, loginId, password, id);
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}

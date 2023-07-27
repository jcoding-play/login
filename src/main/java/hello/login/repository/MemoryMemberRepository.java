package hello.login.repository;

import hello.login.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public void clearStore() {
        store.clear();
    }

    @Override
    public Member save(Member member) {
        member.save(store, ++sequence);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        List<Member> members = findAll();

        return members.stream()
                .filter(member -> member.isLoginIdMatch(loginId))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean hasDuplicatedLoginId(Member member) {
        List<Member> members = findAll();

        return members.stream()
                .anyMatch(member::isDuplicatedLoginId);
    }
}

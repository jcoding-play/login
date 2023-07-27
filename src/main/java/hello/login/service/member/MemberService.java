package hello.login.service.member;

import hello.login.domain.member.Member;
import hello.login.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        validateDuplicatedLoginId(member);
        memberRepository.save(member);
    }

    private void validateDuplicatedLoginId(Member member) {
        if (memberRepository.hasDuplicatedLoginId(member)) {
            throw new IllegalArgumentException("이미 이용중인 아이디입니다.");
        }
    }

    public Optional<Member> findMember(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
}

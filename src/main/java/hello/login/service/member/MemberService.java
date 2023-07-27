package hello.login.service.member;

import hello.login.domain.member.Member;
import hello.login.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private Logger log = LoggerFactory.getLogger(getClass());

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        validateDuplicatedLoginId(member);
        memberRepository.save(member);
        log.info("savedMember={}", member);
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

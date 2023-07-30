package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 *
 * 회원 서비스 개발
 *
 * @Transactional : 데이터를 저장하고 변경할때는 항상 Transactional이 있어야 된다.
 *
 */

@Transactional
public class MemberService {


//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    /**
     * 참고: 생성자에 @Autowired 를 사용하면 객체 생성 시점 => 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입 => 생성자 1개만 있으면 @Autowired 생략
     *
     * @param memberRepository
     */
    @Autowired
    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;

    }

    /**
     * 회원 가입
     *
     *
     */

    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     *
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }



}

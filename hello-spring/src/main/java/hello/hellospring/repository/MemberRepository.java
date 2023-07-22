package hello.hellospring.repository;


import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

/**
 * 회원 리포지토리 인터페이스
 *
 * 인터페이스로 구현 클래스를 변경할 수 있도록 설계 => 데이터 저장소를 결정하지 않은 상황이기 때문이다.
 *
 */

public interface MemberRepository {
        Member save(Member member);

        Optional<Member> findById(Long id);
        Optional<Member> findByName(String name);
        List<Member> findAll();

}

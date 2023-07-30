package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *Command + B => 내장된 메소드 탐방
 * Command + E => 메소드들 목록뜸.
 *
 * 복잡한 동적 쿼리 -> Querydsl
 */
public interface SpringDataJpaMemberRepository  extends JpaRepository<Member, Long>, MemberRepository {
    //JPQL select m from Member m where m.name = ?
    // Interface 이름 만으로도 개발이 끝
    // 메서드 네임 , 반환 이름 , 타입만 수정해서 사용 가능
    // findByNameAndId(String name , ...)
    Optional<Member> findByName(String name);
}

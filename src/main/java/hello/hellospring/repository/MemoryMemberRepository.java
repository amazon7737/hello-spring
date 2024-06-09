package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;


/**
 *
 * 회원 리포지토리 메모리 구현체
 *
 *
 *
 */

/**
 * @Repository => 회원 리포지토리 스프링 빈 등록
 *
 * 스프링 컨테이너
 * memberController -> memberService -> memberRepository
 *
 * 참고: 스프링 컨테이너에 스프링 빈 등록할때 , 기본으로 싱글톤으로 등록 (유일하게 하나만 등록해서 공유)
 * 같은 스프링 빈이면 모두 같은 인스턴스, 특별한 경우 제외 대부분 싱글톤 사용
 */
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 캐시 정리 이게 없으면 문제가 발생함 자세한 문제는 알아보자.
    public void clearStore() {
        store.clear();
    }
}

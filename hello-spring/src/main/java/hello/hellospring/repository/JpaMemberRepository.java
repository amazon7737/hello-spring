package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * Implements  method
 * Option + Enter
 *
 *
 */
public class JpaMemberRepository  implements MemberRepository{

    /**
     * Data-jpa 라이브러리 받음 => spring boot 가 자동으로 entitymanager를 생성해준다
     * 이걸 injection 받아서 사용하면됨.
     *
     * 내부적으로 DataSource 를 들고있어서 db랑 통신하는걸 알아서 해준다.
     */
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    /**
     *
     * JPQL ..
     * Spring data jpa : findByName , findAll 은 jpql을 작성해줘야됨
     * spring에서 jpa를 한번 감싸서 해주는게 있는데
     * jpql안짜고도 가능하다.
     *
     */
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        /**
         * Option + Command + N => 단축시키기
         * 객체를 대상으로 쿼리를 날림.
         * member entity 자체를 select 하는거라서 m으로 검색
          */
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();

    }
}

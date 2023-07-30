package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Web Application 띄워서 검증할 필요 X => 스프링 통합 테스트를 돌려보면됨.
 *
 *  DataSource : 데이터 베이스 커넥션 흭득을 위해 사용하는 객체
 *  스프링 부트는 데이터베이스 커넥션 정보 -> DataSource를 생성 -> 스프링 빈으로 만듬
 *  이걸로 DI를 받을 수 있다.
 *
 * 개방 폐쇄 원칙(OCP , Open-Closed Principle) : 확장에는 열려있고 , 수정/변경에는 닫혀있다.
 * 스프링 DI => 기존 코드를 손대지 않고, 설정만으로 구현 클래스 변경이 가능하다.
 *
 */

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }


    /**
     * EntityManager , jpa
     *
     */

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    }

    /**
     *
     * DataSource , jdbc
     */

//    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }


//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository;
//    }
}
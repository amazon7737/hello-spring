package hello.hellospring.service;
import hello.hellospring.domain.Member;

import hello.hellospring.repository.MemoryMemberRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 회원 서비스 테스트
 * @BeforeEach 는 무엇인지 => 각 테스트 실행전에 호출된다. 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고, 의존관계도 새로 맺어준다.
 * @AfterEach 는 무엇인지
 *
 *  assertEquals => JUnit 에서 들고온 메소드인데
 *  여기에 두가지 값을 넣으면 알아서 비교하여 체크해준다.
 *
 *
 */
class MemberServiceTest {
    MemberService memberService;

    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService((memberRepository));
        
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    public void 회원가입() throws Exception{
        //Given
        Member member = new Member();
        member.setName("hello");

        //When
        Long saveId = memberService.join(member);

        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());

    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //Given
        Member member1 = new Member();
        member1.setName("spring");;

        Member member2 = new Member();
        member2.setName("spring");

        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try{
//            memberService.join(member1);
//            fail();
//        }catch(IllegalStateException e){
//
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


    }
        // then
        @Test
        void findMembers(){

        }

        @Test
        void findOne() {

    }

}

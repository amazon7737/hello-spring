package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @SpringBootTest : 스프링 컨테이너 , 테스트 실행
 * @Transactional : 애노테이션 존재 , 테스트 시작 전 트랜잭션 시작 , 테스트 완료 후 항상 롤백 => DB에 데이터를 남기지 않음. 다음 테스트 영향 X
 *
 * 테스트 잘 작성하는 거에서 고민하고, 꼭 테스트를 해야한다.
 */


@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    /**
     *
     * @Commit : db에 반영이 된다
     *
     */
    @Test
    public void 회원가입() throws Exception {
        // Given
        Member member = new Member();
        member.setName("spring4");

        // When
        Long saveId = memberService.join(member);

        // Then
        Member findMember = memberRepository.findById(saveId).get();
        Assertions.assertEquals(member.getName(), findMember.getName());



    }

    @Test
    public void 중복_회원_예외() throws Exception{
        /**
         * member1 , member2 객체에 현재 DB에 없는 회원을 넣어주면서
         * DB에는 데이터가 없지만 가짜 데이터를 넣어서 한번 던져 볼 수 있다.
         * member1==member2 가 되야하므로 중복 회원일 경우, 테스트 통과
         * 이미 DB에 있는 회원이라면 :이미 존재하는 회원입니다."라는 메시지를 남긴다.
         *
         * 테스트 코드 꺠달은점.
         * 해당 기능을 가상으로 돌려보면서 기능이 잘 돌아가는지 확인해 볼 수 있는 것 같다.
         *
         */
        // Given
        Member member1 = new Member();
        member1.setName("spring5");

        Member member2 = new Member();
        member2.setName("spring5");

        // When
        memberService.join(member1);
      IllegalStateException e = assertThrows(IllegalStateException.class , () -> memberService.join(member2)); // 예외가 발생해야 한다.
        // 단축키 잘 체크해놓기.
        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }


}

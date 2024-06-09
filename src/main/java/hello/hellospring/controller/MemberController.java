package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * [컴포넌트 스캔과 자동 의존관계 설정]
 * 회원 컨트롤러에 의존관계 추가
 *
 *
 * memberService가 스프링 빈으로 등록되어 있지 않다.
 *
 * 참고: helloController는 스프링이 제공하는 컨트롤러여서 스프링 빈으로 자동 등록된다
 * @Controller가 있으면 자동 등록됨
 *
 *
 * @Autowired => 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
 * 객체 의존관계를 외부에서 넣어주는 것을 => DI(Dependency Injection) "의존성 주입"이라고 한다.
 *
 * 이전 테스트는 개발자가 직접 주입했지만 여기서는 자동으로 주입됨.
 */
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;

    }

    /**
     * [회원 웹 기능 - 등록]
     *
     * 회원 등록 폼 개발
     *
     * 회원 등록 폼 컨트롤러
     *
     */
    @GetMapping(value = "/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }


    /**
     * 회원 컨트롤러에서 회원을 실제 등록하는 기능
     *
     *
     */
    @PostMapping(value = "/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    /**
     * 회원 컨트롤러에서 조회 기능
     *
     *
     */
    @GetMapping(value = "/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }


}


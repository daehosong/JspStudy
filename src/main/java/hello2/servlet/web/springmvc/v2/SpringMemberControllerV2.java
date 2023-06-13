package hello2.servlet.web.springmvc.v2;

import hello2.servlet.domain.member.Member;
import hello2.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
    // RequestMapping은 메서드 단위로 넣을 수 있기 때문에 원하는 만큼 클래스에 넣을 수 있음.
    // 단, 어느정도 연관성 있는 컨트롤러끼리 넣어주자.
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //  @RequestMapping("/springmvc/v2/members/new-form") 형태를 살펴보면
    //  -> 세가지 메서드 공통부분 /springmvc/v2/members 위로 뺼 수 있음
    @RequestMapping("/new-form")
    public ModelAndView newForm(){
        return new ModelAndView("new-form");
    }
    @RequestMapping
    public ModelAndView members() {

        List<Member> members= memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members",members);
        return mv;
    }
    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username,age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member",member);
        return mv;
    }

}

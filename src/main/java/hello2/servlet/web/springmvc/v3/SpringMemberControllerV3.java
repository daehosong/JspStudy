package hello2.servlet.web.springmvc.v3;

import hello2.servlet.domain.member.Member;
import hello2.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    //  1.  현재는 RequestMapping을 통하여
    //      GET,POST,PUT 방식을 고려하지 않은 작성 방법
    //      @RequestMapping("/new-form") , @RequestMapping("/save") , @RequestMapping("/members")
    //  2.  @RequestMapping(value = "/new-form",method = RequestMethod.GET)으로 방식 설정
    //  3.  2번 방법이 길다고 느껴서 @GetMapping, @PostMapping으로 간편한 방식 사용
    //      Get,Post,Put,Delete,Patch 모두 있음.

    //  @RequestMapping(value = "/new-form",method = RequestMethod.GET)
    @GetMapping("new-form")
    public String newForm() {
        return "new-form";
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }

    //  @RequestMapping(value = "/save",method = RequestMethod.POST)
    //  request.getParameter("username")과 @RequestParam은 같은 코드라고 보면 된다.
    //  GET 쿼리 파라미터 방식과 POST Form 방식을 모두 지원한다.
    @PostMapping("/save")
    public String save(@RequestParam("username") String username,
                       @RequestParam("age") int age,
                       Model model) {
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }
}
package hello2.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*  1. @Controller Or
    2. @Component   -> 스프링 빈에 등록 됨.
       @RequestMapping ->   RequestMappingHandlerMapping 이 찾아 내준다.
       클래스 단계에서 RequestMapping을 쓸 때 메소드에 RequestMapping 없애 줘야 함. 안 그러면 인식 못함

 */
//@Component
//@RequestMapping
@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }
}

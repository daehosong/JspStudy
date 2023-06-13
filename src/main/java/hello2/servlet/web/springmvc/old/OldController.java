package hello2.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

    // BeanNameUrlHandlerMapping로 OldController가 반환이 됨
@Component("/springmvc/old-controller")
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("Good");
        return new ModelAndView("new-form");
    }
    // properties에서 prefix suffix 기능을 하는 뷰 리졸버
    /*


     */
}

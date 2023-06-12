package hello2.servlet.web.frontcontroller.v2;

import hello2.servlet.web.frontcontroller.MyView;
import hello2.servlet.web.frontcontroller.v1.ControllerV1;
import hello2.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello2.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello2.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//      front-controller/v2/* << * 이면 아래 하위에 있는 모든 것을 불러옴
@WebServlet(name = "FrontControllerV2",urlPatterns = "/front-controller/v2/*")
public class FrontControllerV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerV2() {
        controllerMap.put("/front-controller/v2/members/new-form",new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save",new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members",new MemberListControllerV2());
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerV2");
        String requestURI = req.getRequestURI();
        ControllerV2 controller =controllerMap.get(requestURI);
        if(controller==null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // New MyView("/WEB-INF/views/new-form.jsp");
        MyView view = controller.process(req, resp);
        view.render(req, resp);
    }
}

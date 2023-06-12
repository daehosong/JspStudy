package hello2.servlet.web.frontcontroller.v3;

import hello2.servlet.web.frontcontroller.ModelView;
import hello2.servlet.web.frontcontroller.MyView;
import hello2.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello2.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello2.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//      front-controller/v2/* << * 이면 아래 하위에 있는 모든 것을 불러옴
@WebServlet(name = "FrontControllerV3",urlPatterns = "/front-controller/v3/*")
public class FrontControllerV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerV3() {
        controllerMap.put("/front-controller/v3/members/new-form",new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save",new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members",new MemberListControllerV3());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
            response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap
        /*
        디테일함
        Map<String,String>paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName->paramMap.put(paramName,
                        request.getParameter(paramName)));
        */

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();     // 논리이름 : new-form

        // MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        // 위 처럼 구체적인 코드가 들어가는 것 보단 대칭을 맞춰서 구현. (viewResolver 사용)
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    //createParamMap()
    //HttpServletRequest에서 파라미터 정보를 꺼내서 Map으로 변환한다. 그리고 해당 Map( paramMap )을
    //컨트롤러에 전달하면서 호출한다.

    private Map<String, String> createParamMap(HttpServletRequest request) {
    Map<String,String> paramMap=new HashMap<>();
    request.getParameterNames().asIterator()
            .forEachRemaining(paramName->paramMap.put(paramName,request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
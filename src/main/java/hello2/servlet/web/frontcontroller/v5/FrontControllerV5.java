package hello2.servlet.web.frontcontroller.v5;

import hello2.servlet.web.frontcontroller.ModelView;
import hello2.servlet.web.frontcontroller.MyView;
import hello2.servlet.web.frontcontroller.v3.ControllerV3;
import hello2.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello2.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello2.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello2.servlet.web.frontcontroller.v4.ControllerV4;
import hello2.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello2.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello2.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello2.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello2.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.taglibs.standard.lang.jstl.ImplicitObjects.createParamMap;

@WebServlet(name="frontControllerServletV5",urlPatterns = "/front-controller/v5/*")
public class FrontControllerV5 extends HttpServlet {

    /* 기존에는
       private Map<String, ControllerV4> controllerMap = new HashMap<>(); 와 같이 한개를 선택 했다면,
       이제는 Object를 넣어 어떤 것을 넣어도 연결이 될 수 있도록 함
     */

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapter = new ArrayList<>();

    public FrontControllerV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapter.add(new ControllerV3HandlerAdapter());
        handlerAdapter.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        // V4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 요청 정보를 가지고 객체 핸들러를 찾아오기
        // 1번과정
        Object handler = getHandler(request);

        //MemberFormControllerV3
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //ControllerV3HandlerAdapter
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView mv = adapter.handle(response,request,handler);

        String viewName = mv.getViewName();     // 논리이름 : new-form
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        //MemberFormHandlerV3
        for (MyHandlerAdapter adapter : handlerAdapter) {
            if(adapter.supports(handler)) {
                return adapter;
            }
        }
        // 어댑터를 못 찾을 경우 예외문 터뜨림
        throw new IllegalArgumentException("handler not found. handler= " +handler);
    }
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);

    }


}
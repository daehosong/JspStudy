package hello2.servlet.web.frontcontroller.v5.adapter;

import hello2.servlet.web.frontcontroller.ModelView;
import hello2.servlet.web.frontcontroller.v3.ControllerV3;
import hello2.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        //MemberFormControllerV3
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletResponse response, HttpServletRequest request, Object handler) throws IOException, ServletException {
        //MemberFormControllerV3
        ControllerV3 controller = (ControllerV3) handler;

        Map<String,String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap=new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName->paramMap.put(paramName,request.getParameter(paramName)));
        return paramMap;
    }
}
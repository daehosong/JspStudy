package hello2.servlet.web.frontcontroller.v5;

import hello2.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    boolean supports(Object handler);

    ModelView handle(HttpServletResponse response, HttpServletRequest request,Object handler) throws IOException, ServletException;
}

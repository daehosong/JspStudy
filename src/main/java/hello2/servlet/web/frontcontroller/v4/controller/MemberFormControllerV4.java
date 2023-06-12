package hello2.servlet.web.frontcontroller.v4.controller;

import hello2.servlet.web.frontcontroller.ModelView;
import hello2.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
    return "new-form";
    }

}
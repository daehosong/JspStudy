package hello2.servlet.web.frontcontroller.v4;

import hello2.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV4 {
    //단축키 /**
    /**
     *
     * @param paramMap
     * @param model
     * @return
     */
    String process(Map<String,String> paramMap,Map<String,Object> model);

}

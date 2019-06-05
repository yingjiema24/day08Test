package net.wanho.ExeceptionResovler;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2019/6/5.
 */
public class globalExceptionResolve implements HandlerExceptionResolver {

    private Logger logger =Logger.getLogger(globalExceptionResolve.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //异常处理逻辑
        logger.error("发生了异常，异常为"+e.getMessage());
        e.printStackTrace();

        //发邮件
        //发信息
        //异常处理，分不同的错误展示错误界面
        if(e instanceof IndexOutOfBoundsException){

        }
        //错误界面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","服务器正忙");
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
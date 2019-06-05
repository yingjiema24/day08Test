package net.wanho.controller;

import net.wanho.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/6/5.
 */
@Controller
public class LoginController {

    @RequestMapping("toSuccess")
    public String toSuccess(){
        return "success";
    }

    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("login")
    public String login(User user ,String loginToken, HttpSession session , HttpServletResponse response){
        if(user==null){
            System.out.println("传入参数有误");
        }
        String username = user.getUsername();
        String password = user.getPassword();
        if("zs".equals(username)&&"123456".equals(password)){
            session.setAttribute("user",user);
            if("loginToken".equals(loginToken)){
                Cookie cookie = new Cookie("user",username+"#"+password);
                cookie.setMaxAge(5*24*3600);
                response.addCookie(cookie);
            }
            return "redirect:/toSuccess";
        }
        return "redirect:/toLogin";
    }
}

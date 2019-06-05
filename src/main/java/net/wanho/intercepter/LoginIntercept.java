package net.wanho.intercepter;

import net.wanho.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/6/5.
 */
public class LoginIntercept extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            response.sendRedirect("toSuccess");
            return false;
        }
        Cookie[] cookies = request.getCookies();

        Cookie cookie = getCookie(cookies);
        if (cookie != null) {
            String username = cookie.getValue().split("#")[0];
            String password = cookie.getValue().split("#")[1];
            if ("zs".equals(username) && "123456".equals(password)) {
                session.setAttribute("user", new User(username, password));
                response.sendRedirect("toSuccess");
                return false;

            }
        }
        return true;
    }

    public Cookie getCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }
}

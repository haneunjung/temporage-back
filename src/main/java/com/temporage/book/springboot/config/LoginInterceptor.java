package com.temporage.book.springboot.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    private final String LOGIN = "login";
    private final String USER = "user";



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if(session.getAttribute(LOGIN) != null){
            session.removeAttribute(LOGIN);
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        //ModelMap modelMap = modelAndView.getModelMap();

        if(session.getAttribute(USER) != null){
            session.setAttribute(LOGIN, session.getAttribute(USER));
        }
    }
}

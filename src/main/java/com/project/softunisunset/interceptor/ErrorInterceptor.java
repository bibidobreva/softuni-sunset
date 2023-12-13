package com.project.softunisunset.interceptor;

import com.project.softunisunset.models.enums.UserRolesEnums;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public class ErrorInterceptor implements HandlerInterceptor {

    public ErrorInterceptor() {
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


            if (!request.getRequestURI().equals("/events/add") && !request.getRequestURI().equals("/changeUserRole") &&
                    (authentication == null || authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals(UserRolesEnums.ADMIN.name())))) {

                return true;
            } else {

                if (authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(UserRolesEnums.ADMIN.name()))) {
                    return true;
                } else {

                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
                    return false;
                }
            }
        } catch (Exception e) {

            response.sendRedirect("/home?accessDenied=true");
            e.printStackTrace();
            return false;
        }
    }





    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        System.out.println("Response Status: " + response.getStatus());
    }


}

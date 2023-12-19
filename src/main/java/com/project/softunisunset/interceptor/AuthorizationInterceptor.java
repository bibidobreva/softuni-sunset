package com.project.softunisunset.interceptor;

import com.project.softunisunset.models.enums.UserRolesEnums;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


public class AuthorizationInterceptor implements HandlerInterceptor {

    public AuthorizationInterceptor() {
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        System.out.println("Request URI: " + request.getRequestURI());
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


            if (!notAllowedPath(request.getRequestURI())) {
                return true;
            } else {
                if (isAdmin(authentication)){
                    return true;
                }
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.sendRedirect("/events");

                return false;
            }
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    private boolean isAdmin(Authentication authentication) {
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_"+UserRolesEnums.ADMIN.name()));
    }

    private boolean notAllowedPath(String requestURI) {
        return "/events/add".equals(requestURI) || "/changeUserRole".equals(requestURI);
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        System.out.println("Response Status: " + response.getStatus());
    }


}

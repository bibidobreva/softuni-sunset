package com.project.softunisunset.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken) && notAllowedPage(request.getRequestURI())) {

            response.sendRedirect("/home");
            return false;
        }

        return true;
    }

    private boolean notAllowedPage(String requestURI) {
        return "/login".equals(requestURI)||"/register".equals(requestURI)||"/".equals(requestURI);
    }
}

package com.project.softunisunset.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && notAllowedPage(request.getRequestURI())) {
            // User is logged in and trying to access the login page
            response.sendRedirect("/home"); // Redirect to home page or any other desired page
            return false; // Prevent further processing
        }

        return true; // Allow the request to proceed
    }

    private boolean notAllowedPage(String requestURI) {
        return "/login".equals(requestURI)||"/register".equals(requestURI)||"/".equals(requestURI);
    }
}

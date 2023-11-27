package com.project.softunisunset.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Check if the user is authenticated (logged in)
        if (!isLoggedIn(request)) {
            // Redirect to the login page
            response.sendRedirect("/login");
            return false; // Stop further processing
        }
        return true; // Continue with the request processing
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        // Implement your logic to check if the user is logged in
        // For example, check if a user object is present in the session
        return request.getSession().getAttribute("user") != null;
    }
}

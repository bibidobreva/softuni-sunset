package com.project.softunisunset.interceptor;

import com.project.softunisunset.session.LoggedUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private LoggedUser loggedUser;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Check if the user has the ADMIN role
        if (!isAdmin()) {
            // Redirect to an unauthorized page or show an error message
            //TODO
            response.sendRedirect("/unauthorized");
            return false; // Stop further processing
        }
        return true; // Continue with the request processing
    }

    private boolean isAdmin() {
        // Check if the user has the ADMIN role
        return "ADMIN".equals(loggedUser.getRole());
    }
}

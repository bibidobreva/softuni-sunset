//package com.project.softunisunset.interceptor;
//
//import com.project.softunisunset.session.LoggedUser;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//public class AuthenticationInterceptor implements HandlerInterceptor {
//    private LoggedUser loggedUser;
//
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        // Check if the user is authenticated (logged in)
//        if (!isLoggedIn()) {
//            // Redirect to the login page
//            response.sendRedirect("/login");
//            return false; // Stop further processing
//        }
//        return true; // Continue with the request processing
//    }
//
//    private boolean isLoggedIn() {
//        // Check if the user is present in the session using the LoggedUser component
//        return loggedUser.getId() != 0; // Adjust this condition based on your implementation
//
//    }
//}

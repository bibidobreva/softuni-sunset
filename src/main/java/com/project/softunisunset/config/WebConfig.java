package com.project.softunisunset.config;

import com.project.softunisunset.interceptor.AdminInterceptor;
import com.project.softunisunset.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        // Register the AuthenticationInterceptor and specify the paths where it should be applied
        //TODO
        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/home", "/blog", "/profile");


        // Register the AdminInterceptor and specify the paths where it should be applied
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin-page");
    }


}

package com.project.softunisunset.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Register the AuthenticationInterceptor and specify the paths where it should be applied
       // registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/feed", "/blog", "/profile");
    }
}

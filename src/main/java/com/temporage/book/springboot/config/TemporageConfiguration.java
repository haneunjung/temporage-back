package com.temporage.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableJdbcHttpSession
public class TemporageConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> sessionCheckUrlPattern = Arrays.asList("/category/*", "/boards/*");

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/sign-in");

        registry.addInterceptor(new SessionCheckInterceptor())
                .addPathPatterns(sessionCheckUrlPattern);

    }
}

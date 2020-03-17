package com.myproject.cloud.config;

import com.myproject.cloud.service.StudentPagingService;
import com.myproject.cloud.service.StudentPagingServiceImpl;
import com.myproject.cloud.service.StudentService;
import com.myproject.cloud.service.StudentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
Main application configuration to define spring beans
 */
@Configuration
public class AppConfig {

    @Bean
    public StudentService studentService() {
        return new StudentServiceImpl();
    }

    @Bean
    public StudentPagingService studentPagingService() {
        return new StudentPagingServiceImpl();
    }
}

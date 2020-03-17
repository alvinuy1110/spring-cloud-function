package com.myproject.cloud.config;

import com.myproject.cloud.domain.Student;
import com.myproject.cloud.service.StudentPagingService;
import com.myproject.cloud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class FunctionConfig {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentPagingService studentPagingService;

    @Bean
    public Function<Long, Student> getStudent() {
        return value -> {
            Student student = null;
            student = studentService.getStudent(value);
            return student;
        };
    }
}

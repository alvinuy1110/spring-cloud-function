package com.myproject.cloud.config;

import com.myproject.cloud.domain.Student;
import com.myproject.cloud.service.StudentPagingService;
import com.myproject.cloud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.catalog.BeanFactoryAwareFunctionRegistry;
import org.springframework.cloud.function.context.catalog.FunctionInspector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.Nullable;
import org.springframework.messaging.converter.CompositeMessageConverter;

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




    // NOTE had to register this as getting an error
//	No qualifying bean of type 'org.springframework.cloud.function.context.catalog.FunctionInspector'
    // THIS IS A HACK FOR NOW
    @Bean
    @Primary
    FunctionInspector functionInspector(ConversionService conversionService, @Nullable CompositeMessageConverter messageConverter) {
        return new BeanFactoryAwareFunctionRegistry(conversionService, messageConverter);
//		return new InMemoryFunctionCatalog();
    }
}

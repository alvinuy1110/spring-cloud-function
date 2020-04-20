package com.myproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.catalog.BeanFactoryAwareFunctionRegistry;
import org.springframework.cloud.function.context.catalog.FunctionInspector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.Nullable;
import org.springframework.messaging.converter.CompositeMessageConverter;

@SpringBootApplication
public class OpenwhiskAdapterApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(OpenwhiskAdapterApp.class, args);
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

package com.myproject.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

@SpringBootApplication
public class CloudFunctionApp {

    public static void main(String[] args) {
        SpringApplication.run(CloudFunctionApp.class, args);
    }

    @Bean
    public Function<String, String> reverseString() {
        return value -> new StringBuilder(value).reverse().toString();
    }

    @Bean
    public Function<String, String> uppercase() {
        return value -> new StringBuilder(value).toString().toUpperCase();
    }

    @Bean
    public Supplier<Integer> supplier() {

        final AtomicInteger nextInt = new AtomicInteger(0);
        IntSupplier supplier = () -> nextInt.getAndIncrement();

        Supplier<Integer> s = () -> {
            return supplier.getAsInt();
        };
        return s;
    }
    @Bean
    public Consumer<String> consumer() {

        Consumer<String> consumer = s -> {
            System.out.println("Incoming string " + s);

        };
        return consumer;
    }
}
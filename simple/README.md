Spring Cloud Function
=====================

# Guide

1. Create a Function
1. Declare the Function as a SpringBean

## Function/ Supplier/ Consumer

When creating either Function/ Supplier/ Consumer, refer to https://cloud.spring.io/spring-cloud-function/reference/html/spring-cloud-function.html#_standalone_web_applications 

## Expose Function via HTTP

With the web configurations activated your app will have an MVC endpoint (on "/" by default, but configurable with spring.cloud.function.web.path) that can be used to access the functions in the application context. The supported content types are plain text and JSON.

Add the dependency

```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-function-web</artifactId>
            <version>${spring.cloud.function.version}</version>
        </dependency>
```


# Start

## As a Spring Boot App
```
mvn spring-boot:run
```

# Commands

## Function Reverse
```
curl localhost:10080/reverseString -H "Content-Type: text/plain" -d "Hello World"
```

## Function Uppercase
```
curl localhost:10080/uppercase -H "Content-Type: text/plain" -d "Hello World"
```

## Function Supplier
```
curl localhost:10080/supplier
```

## Function Consumer
```
curl localhost:10080/consumer -H "Content-Type: text/plain" -d "Hello World"
```

# Deployment

See https://cloud.spring.io/spring-cloud-function/reference/html/spring-cloud-function.html#_deploying_a_packaged_function

You can deploy in different variants, each with different settings and pros/cons.

* simple jar 
* spring boot jar
* spring boot app

# Testing

## As Spring Boot Test

see WebFunctionTest

## As a Regular Function

see FunctionTest

# Cloud Provider References

## Azure

* https://docs.microsoft.com/en-us/azure/java/spring-framework/getting-started-with-spring-cloud-function-in-azure

# References

* https://cloud.spring.io/spring-cloud-static/spring-cloud-function/3.0.2.RELEASE/reference/html/
* https://cloud.spring.io/spring-cloud-function/reference/html/spring-cloud-function.html

## Tutorial

Getting Started with AWS support
* https://www.baeldung.com/spring-cloud-function


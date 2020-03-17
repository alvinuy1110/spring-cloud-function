Spring Cloud Function JPA
=========================

This demonstrates JPA functionality but exposed as a Function.


# Start

## As a Spring Boot App
```
mvn spring-boot:run
```

# Commands

## Function getStudent
```
curl -v localhost:10080/getStudent/1 
```
 
# Exception Handling

When doing REST, Spring will still honor the @ControllerAdvice and/or ExceptionHandler, if you have one.

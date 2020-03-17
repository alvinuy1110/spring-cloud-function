package com.myproject.cloud;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test like a regular function
 */
@FunctionalSpringBootTest(classes = CloudFunctionApp.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Slf4j
public class FunctionTest {

    @Autowired
    private FunctionCatalog catalog;

    @Test
    public void data_reverseString_responseCorrect() throws Exception {

        Function<String, String> function = catalog.lookup(Function.class,
                "reverseString");

        String result = function.apply("hello");
       log.info(result);
       // then
       assertThat(result).isEqualTo("olleh");

    }
}
package com.myproject.cloud;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test like a regular spring boot app
 */
@SpringBootTest(classes = CloudFunctionApp.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class WebFunctionTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    public void data_reverseString_responseCorrect() throws Exception {
        ResponseEntity<String> result = this.rest.exchange(
                RequestEntity.post(new URI("/reverseString")).body("hello"), String.class);
       log.info(result.getBody());

       // then
       assertThat(result.getStatusCodeValue()).isEqualTo(200);
       assertThat(result.getBody()).isEqualTo("olleh");

    }

    @Test
    public void data_uppercase_responseCorrect() throws Exception {
        ResponseEntity<String> result = this.rest.exchange(
                RequestEntity.post(new URI("/uppercase")).body("hello"), String.class);
        log.info(result.getBody());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo("HELLO");

    }
}
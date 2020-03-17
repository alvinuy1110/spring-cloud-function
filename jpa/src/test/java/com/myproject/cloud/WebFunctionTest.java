package com.myproject.cloud;

import com.myproject.cloud.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test like a regular spring boot app
 */
@SpringBootTest(classes = CloudFunctionJpaApp.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class WebFunctionTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    public void data_getStudent_responseCorrect() throws Exception {
        RequestEntity<?> requestEntity = null;
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", 1);
        ResponseEntity<Student> result = this.rest.exchange(
                "/getStudent/{id}", HttpMethod.GET, requestEntity, Student.class, uriVariables);
        log.info("Student: " + result.getBody());

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        Student student = result.getBody();
        assertThat(student.getFirstName()).isEqualTo("John");
        assertThat(student.getLastName()).isEqualTo("Doe");
        assertThat(student.getBirthDate()).isEqualTo("2001-06-23");

    }

}
package com.myproject.cloud.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

/**
 * This is sample of a DTO for errors when returned through the API
 */
@Getter
@AllArgsConstructor
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;

}

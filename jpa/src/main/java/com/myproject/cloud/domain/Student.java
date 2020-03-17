package com.myproject.cloud.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Student {

    private long id;
    private String firstName;
    private String lastName;
    private String studentNumber;
    private Date birthDate;
}

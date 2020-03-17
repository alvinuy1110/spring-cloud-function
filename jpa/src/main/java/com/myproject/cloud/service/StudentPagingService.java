package com.myproject.cloud.service;

import com.myproject.cloud.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentPagingService extends  StudentService{

    Page<Student> getStudents(Pageable pageable);
}

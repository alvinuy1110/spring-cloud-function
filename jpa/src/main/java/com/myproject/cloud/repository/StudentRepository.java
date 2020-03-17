package com.myproject.cloud.repository;

import com.myproject.cloud.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Define the repository definition of how to interact with the datasource
 *
 * THings to note:
 *
 * * CrudRepository - performs the CRUD (create, read, update, delete) functionality
 * * CrudRepository<> generic takes (1) the Entity object, (2) the Primary ID data type (check the Entity @Id
 * annotation type)
 */
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {

}

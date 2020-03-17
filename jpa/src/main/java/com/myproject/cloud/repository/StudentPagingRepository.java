package com.myproject.cloud.repository;

import com.myproject.cloud.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Define the repository definition of how to interact with the datasource.
 *
 * This one uses Pagination.  Useful when retrieving lists that not everything if fetched
 *
 * THings to note:
 *
 * * JpaRepository - has both CRUD functionality and Paging capability
 * * JpaRepository<> generic takes (1) the Entity object, (2) the Primary ID data type (check the Entity @Id
 * annotation type)
 */
public interface StudentPagingRepository extends JpaRepository<StudentEntity, Long> {

}

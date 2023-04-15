package com.example.StudentApi.repository;

import com.example.StudentApi.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, Long> {
}

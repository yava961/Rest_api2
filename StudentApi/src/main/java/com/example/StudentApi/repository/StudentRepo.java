package com.example.StudentApi.repository;

import com.example.StudentApi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
}

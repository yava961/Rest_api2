package com.example.StudentApi.repository;

import com.example.StudentApi.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
}

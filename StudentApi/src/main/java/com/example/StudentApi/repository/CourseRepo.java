package com.example.StudentApi.repository;

import com.example.StudentApi.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepo extends CrudRepository<Course, Long> {
}

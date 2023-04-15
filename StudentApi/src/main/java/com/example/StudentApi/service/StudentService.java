package com.example.StudentApi.service;

import com.example.StudentApi.entity.Course;
import com.example.StudentApi.entity.Student;
import com.example.StudentApi.entity.dto.CourseDTO;
import com.example.StudentApi.entity.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO);

    Student addCourseToStudent(Long studentId, Long courseId);

    List<CourseDTO> getCoursesForStudent(Long studentId);

    Student removeCourseFromStudent(Long studentId, Long courseId);
}

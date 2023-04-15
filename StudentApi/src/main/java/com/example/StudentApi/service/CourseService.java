package com.example.StudentApi.service;

import com.example.StudentApi.entity.Course;
import com.example.StudentApi.entity.Student;
import com.example.StudentApi.entity.dto.CourseDTO;
import com.example.StudentApi.entity.dto.StudentDTO;

import java.util.List;

public interface CourseService {
    CourseDTO createCourse(CourseDTO courseDTO);

    Course addStudentToCourse(Long courseId, Long studentId);

    List<StudentDTO> getStudentsForCourse(Long courseId);

    Course removeStudentFromCourse(Long courseId, Long studentId);
}

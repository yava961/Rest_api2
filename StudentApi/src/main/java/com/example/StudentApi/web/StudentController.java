package com.example.StudentApi.web;

import com.example.StudentApi.entity.Course;
import com.example.StudentApi.entity.Student;
import com.example.StudentApi.entity.dto.CourseDTO;
import com.example.StudentApi.entity.dto.StudentDTO;
import com.example.StudentApi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        try {
            StudentDTO createdStudent = studentService.createStudent(studentDTO);
            return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<Student> addCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        try {
            Student student = studentService.addCourseToStudent(studentId, courseId);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<CourseDTO>> getCoursesForStudent(@PathVariable Long studentId) {
        try {
            List<CourseDTO> courses = studentService.getCoursesForStudent(studentId);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{studentId}/courses/{courseId}")
    public ResponseEntity<Student> removeCourseFromStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        try {
            Student student = studentService.removeCourseFromStudent(studentId, courseId);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}

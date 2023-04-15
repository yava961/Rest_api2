package com.example.StudentApi.web;

import com.example.StudentApi.entity.Course;
import com.example.StudentApi.entity.Student;
import com.example.StudentApi.entity.dto.CourseDTO;
import com.example.StudentApi.entity.dto.StudentDTO;
import com.example.StudentApi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        try {
            CourseDTO createdCourse = courseService.createCourse(courseDTO);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<Course> addStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        try {
            Course course = courseService.addStudentToCourse(courseId, studentId);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<List<StudentDTO>> getStudentsForCourse(@PathVariable Long courseId) {
        try {
            List<StudentDTO> students = courseService.getStudentsForCourse(courseId);
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<Course> removeStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        try {
            Course course = courseService.removeStudentFromCourse(courseId, studentId);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

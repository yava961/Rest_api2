package com.example.StudentApi.service.impl;

import com.example.StudentApi.entity.Course;
import com.example.StudentApi.entity.Student;
import com.example.StudentApi.entity.dto.CourseDTO;
import com.example.StudentApi.entity.dto.StudentDTO;
import com.example.StudentApi.repository.CourseRepo;
import com.example.StudentApi.repository.StudentRepo;
import com.example.StudentApi.service.StudentService;
import com.example.StudentApi.service.exeption.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentRepo.save(modelMapper.map(studentDTO, Student.class));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public Student addCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new NotFoundException("Student not found"));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new NotFoundException("Course not found"));

        course.enrollStudent(student);
        return studentRepo.save(student);
    }

    @Override
    public List<CourseDTO> getCoursesForStudent(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        List<Course> courses = student.getCourses();
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public Student removeCourseFromStudent(Long studentId, Long courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new NotFoundException("Course not found"));
        List<Student> students = course.getStudents();

        if (students == null || students.isEmpty()) {
            throw new NotFoundException("Student not found in the course");
        }

        Student targetStudent = students.stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Student not found in the course"));

        students.remove(targetStudent);
        courseRepo.save(course);

        List<Course> courses = targetStudent.getCourses();
        courses.remove(course);
        targetStudent.setCourses(courses);
        return studentRepo.save(targetStudent);
    }


}

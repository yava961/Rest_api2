package com.example.StudentApi.service.impl;

import com.example.StudentApi.entity.Course;
import com.example.StudentApi.entity.Student;
import com.example.StudentApi.entity.dto.CourseDTO;
import com.example.StudentApi.entity.dto.StudentDTO;
import com.example.StudentApi.repository.CourseRepo;
import com.example.StudentApi.repository.StudentRepo;
import com.example.StudentApi.service.CourseService;
import com.example.StudentApi.service.exeption.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = courseRepo.save(modelMapper.map(courseDTO, Course.class));
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public Course addStudentToCourse(Long courseId, Long studentId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new NotFoundException("Course not found"));
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new NotFoundException("Student not found"));
        List<Student> students = course.getStudents();
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
        course.setStudents(students);
        return courseRepo.save(course);
    }

    @Override
    public List<StudentDTO> getStudentsForCourse(Long courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        List<Student> students = course.getStudents();
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Course removeStudentFromCourse(Long courseId, Long studentId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new NotFoundException("Course not found"));
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new NotFoundException("Student not found"));
        List<Student> students = course.getStudents();
        if (students == null || students.isEmpty()) {
            throw new NotFoundException("Student not found");
        }
        students.remove(student);
        course.setStudents(students);
        return courseRepo.save(course);
    }
}

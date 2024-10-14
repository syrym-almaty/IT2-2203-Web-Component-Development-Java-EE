package com.example.demo.service;

import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CourseService {
    @Autowired
    @Getter
    @Setter
    private StudentRepository studentRepository;

    @Autowired
    @Getter
    @Setter
    private CourseRepository courseRepository;

    public boolean isStudentValidData(UUID id) {
        return studentRepository.findById(id)
                .map(student -> student.getCourses().size() < 5)  // Check if the student has fewer than 5 courses
                .orElse(false);  // Return false if student is not found
    }

    public boolean isCourseValidData(Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> course.getStudents().size() < 30) // true If students fewer than 30
                .orElse(false); // false if Course not found
    }
}

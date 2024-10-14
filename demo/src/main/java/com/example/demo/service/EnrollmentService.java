package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

    @Service
    public class EnrollmentService {
        @Autowired
        private StudentRepository studentRepository;

        @Autowired
        private CourseRepository courseRepository;

        public void enrollStudentInCourse(Long studentId, Long courseId) {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

            if (student.getCourses().size() >= 5) {
                throw new BusinessException("Student cannot enroll in more than 5 courses");
            }

            if (course.getStudents().size() >= 30) {
                throw new BusinessException("Course cannot have more than 30 students");
            }

            student.getCourses().add(course);
            course.getStudents().add(student);

            studentRepository.save(student);
            courseRepository.save(course);
        }
    }
}
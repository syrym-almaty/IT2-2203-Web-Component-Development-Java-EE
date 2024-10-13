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
    CourseService courseService;

    public void enrollStudentInCourse(UUID studentId, Long courseId) throws BusinessException {
        if (courseService.isCourseValidData(courseId)) {
            if (courseService.isStudentValidData(studentId)) {
                Student student = courseService.getStudentRepository()
                        .findById(studentId)
                        .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
                Course course = courseService.getCourseRepository()
                        .findById(courseId)
                        .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

                student.getCourses().add(course);
                course.getStudents().add(student);

                courseService.getStudentRepository().save(student);
                courseService.getCourseRepository().save(course);
            } else {
                throw new BusinessException("Student is enrolled in more than 5 courses.");
            }
        } else {
            throw new BusinessException("Course has more than 30 students.");
        }
    }
}
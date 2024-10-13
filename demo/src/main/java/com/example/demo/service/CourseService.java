package com.example.demo.service;

import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CourseService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public boolean isStudentValidData(UUID id) {
       return studentRepository.findById(id)
                .map(student -> student.getCourses().size() < 5)  // Check if the student has fewer than 5 courses
                .orElse(false);  // Return false if student is not found
    }

    public  boolean isCourseValidData(long uuid){
return courseRepository.findById(uuid).map(course -> course.getStudents().size() < 30).orElse(false);
    }
}


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
      if (courseService.isCourseValidData(courseId)){
          if (courseService.isStudentValidData(studentId)){
          Student student = courseService.getStudentRepository().getReferenceById(studentId);
          Course course = courseService.getCourseRepository().getReferenceById(courseId);

              student.getCourses().add(course);
              course.getStudents().add(student);

              courseService.getStudentRepository().save(student);
              courseService.getCourseRepository().save(course);
          }
          else {
              throw new ResourceNotFoundException("The student not found or Courses more than 5");
          }
      }
      else {
          throw new ResourceNotFoundException("The course not found or Students more than 30 ");
      }


    }
}
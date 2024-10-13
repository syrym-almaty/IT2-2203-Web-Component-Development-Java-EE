package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.GradeRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class GradeService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeRepository gradeRepository;

    public void addGrade(UUID studentId, Long courseId, Double grade) {
        // Получить студента и курс по идентификаторам
        Student student = studentRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        // Создать новую оценку
        Grade gradeEntity = new Grade();
        gradeEntity.setStudent(student);
        gradeEntity.setCourse(course);
        gradeEntity.setGrade(grade);

        // Сохранить оценку
        gradeRepository.save(gradeEntity);
    }

    public void updateGrade(UUID studentId, Long courseId, Double grade) {
        // Получить студента и курс по идентификаторам
        Student student = studentRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        // Найти оценку по идентификаторам студента и курса
        Grade gradeEntity = gradeRepository.findByStudentAndCourse(student, course).orElseThrow();

        // Обновить оценку
        gradeEntity.setGrade(grade);

        // Сохранить изменения
        gradeRepository.save(gradeEntity);
    }

    public void deleteGrade(UUID studentId, Long courseId) {
        // Получить студента и курс по идентификаторам
        Student student = studentRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        // Найти оценку по идентификаторам студента и курса
        Grade gradeEntity = gradeRepository.findByStudentAndCourse(student, course).orElseThrow();

        // Удалить оценку
        gradeRepository.delete(gradeEntity);
    }

    public Double calculateAverageGrade(UUID studentId) {
        // Получить студента по идентификатору
        Student student = studentRepository.findById(studentId).orElseThrow();

        // Найти все оценки студента
        List<Grade> grades = gradeRepository.findByStudent(student);

        // Рассчитать среднюю оценку
        double averageGrade = grades.stream()
                .mapToDouble(Grade::getGrade)
                .average()
                .orElse(0.0);

        return averageGrade;
    }

    public Double calculateGPA(UUID studentId) {
        // Получить студента по идентификатору
        Student student = studentRepository.findById(studentId).orElseThrow();

        // Найти все оценки студента
        List<Grade> grades = gradeRepository.findByStudent(student);

        // Рассчитать GPA
        double gpa = grades.stream()
                .mapToDouble(grade -> {
                    double gradeValue = grade.getGrade();
                    if (gradeValue >= 90) {
                        return 4.0;
                    } else if (gradeValue >= 80) {
                        return 3.0;
                    } else if (gradeValue >= 70) {
                        return 2.0;
                    } else if (gradeValue >= 60) {
                        return 1.0;
                    } else {
                        return 0.0;
                    }
                })
                .average()
                .orElse(0.0);

        return gpa;
    }
}

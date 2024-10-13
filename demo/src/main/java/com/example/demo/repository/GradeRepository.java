package com.example.demo.repository;

import com.example.demo.entity.Grade;
import com.example.demo.entity.GradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface GradeRepository extends JpaRepository<Grade, GradeId> {

    // In GradeRepository.java
    @Query("select g from Grade g where g.student.id = :studentId")
    Set<Grade> findByStudentId(@Param("studentId") UUID studentId);

}
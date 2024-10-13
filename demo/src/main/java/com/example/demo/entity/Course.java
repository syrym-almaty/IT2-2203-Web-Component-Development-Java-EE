package com.example.demo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course name is required")
    private String name;

    @NotBlank(message = "Course code is required")
    @Column(unique = true)
    private String code;

    @NotBlank(message = "Course credit is required")
    @Min(value = 2, message = "Course credits must be at least 2")
    @Max(value = 6, message = "Course credits must not exceed 6")
    private int credits;


    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
}
package com.example.demo.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.MutablePropertyValues;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Student {

    // Getters and Setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    @Column(columnDefinition = "uuid")
    private UUID id;

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String email;

    // Constructors
    public Student() {
    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }


    @Getter
    @ManyToMany
    @JoinTable(
            name = "enrollments",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    // GPA field
    private Double gpa;

}


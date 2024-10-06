package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
<<<<<<< HEAD
import jakarta.persistence.Column;
=======
>>>>>>> origin/student-rasul
import org.hibernate.annotations.GenericGenerator;
import java.util.UUID;

@Entity
public class Student {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
<<<<<<< HEAD
    @Column(columnDefinition = "uuid")
=======
>>>>>>> origin/student-rasul
    private UUID id;

    private String name;
    private String email;

    // Constructors
    public Student() {}

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
	public String getEmail() {
=======
    public String getEmail() {
>>>>>>> origin/student-rasul
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

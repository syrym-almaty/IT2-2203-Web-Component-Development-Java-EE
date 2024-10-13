package com.example.demo.entity;

public class Course {
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

        @ManyToMany(mappedBy = "courses")
        private Set<Student> students = new HashSet<>();
    }
}

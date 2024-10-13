package com.example.demo.entity;

public class Grade {
    @Entity
    @Table(name = "grades")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Grade {
        @EmbeddedId
        private GradeId id = new GradeId();

        @ManyToOne
        @MapsId("studentId")
        private Student student;

        @ManyToOne
        @MapsId("courseId")
        private Course course;

        @NotNull
        private Double score;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class GradeId implements Serializable {
        private Long studentId;
        private Long courseId;
    }
}

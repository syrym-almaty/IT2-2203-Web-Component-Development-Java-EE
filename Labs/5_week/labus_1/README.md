# 🏗️ **Lab Work: Building an Advanced Student Management System**

## **Overview**

In this lab, you will enhance the existing **Student Management System** by incorporating advanced architectural patterns, business logic, and modern software development practices. The goal is to deepen your understanding of **Java Spring Boot**, **RESTful API design**, **design patterns**, and **software architecture**.

---

## **Learning Objectives**

1. **Advanced Architecture**: Implement a layered architecture with clear separation of concerns.
2. **Design Patterns**: Apply design patterns such as **DAO**, **Factory**, **Singleton**, and **Strategy**.
3. **Business Logic**: Implement complex business logic with data validations and calculations.
4. **Data Persistence**: Utilize **Hibernate** for ORM with advanced mappings.
5. **RESTful API**: Design and expose APIs following REST principles.

---

---

## **Lab Assignment**

### **Project Enhancement: Advanced Student Management System**

You are required to enhance the existing Student Management System by adding new features, applying design patterns, and restructuring the application to follow best practices in software architecture.

## **Step-by-Step Instructions**

### **1. Set Up the Project**

### **2. Implement Course Management**

#### **2.1 Create `Course` Entity**

```java
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
```

#### **2.2 Update `Student` Entity**

Add the relationship to `Course`.

```java
@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    // Existing fields...

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
```

#### **2.3 Create `Enrollment` Entity (Optional)**

If you need to store additional information about the enrollment (e.g., enrollment date), create an `Enrollment` entity.

#### **2.4 Create Repositories**

```java
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCode(String code);
}
```

---

### **3. Implement Grade Management**

#### **3.1 Create `Grade` Entity**

```java
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
```

#### **3.2 Update Business Logic**

- In `StudentService`, implement methods to calculate GPA.
- GPA can be calculated as the average of all course grades.

---

### **4. Implement Business Logic Constraints**

#### **4.1 Enforce Enrollment Limits**

- In `CourseService`, before enrolling a student, check:
  - Student is not enrolled in more than 5 courses.
  - Course does not have more than 30 students.

```java
@Service
public class EnrollmentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public void enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if (student.getCourses().size() >= 5) {
            throw new BusinessException("Student cannot enroll in more than 5 courses");
        }

        if (course.getStudents().size() >= 30) {
            throw new BusinessException("Course cannot have more than 30 students");
        }

        student.getCourses().add(course);
        course.getStudents().add(student);

        studentRepository.save(student);
        courseRepository.save(course);
    }
}
```

---

### **5. Apply Design Patterns**

#### **5.1 DAO Pattern**

- Separate data access logic into DAO classes.
- For example, create `StudentDAO`, `CourseDAO` classes.

```java
@Repository
public class StudentDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public void save(Student student) {
        entityManager.persist(student);
    }

    // Other data access methods...
}
```

#### **5.2 Factory Pattern**

- Create a factory for creating instances, such as grading strategies.

```java
public interface GradingStrategy {
    Double calculateGrade(Double score);
}

public class LetterGradingStrategy implements GradingStrategy {
    @Override
    public Double calculateGrade(Double score) {
        // Convert numeric score to letter grade
    }
}

public class GradingStrategyFactory {
    public static GradingStrategy getStrategy(String type) {
        if (type.equalsIgnoreCase("letter")) {
            return new LetterGradingStrategy();
        } else if (type.equalsIgnoreCase("percentage")) {
            return new PercentageGradingStrategy();
        }
        return null;
    }
}
```

#### **5.3 Singleton Pattern**

- Ensure only one instance of certain services or managers exists.

```java
public class ConfigurationManager {
    private static ConfigurationManager instance;

    private ConfigurationManager() {
        // Load configuration
    }

    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }
}
```

#### **5.4 Strategy Pattern**

- Implement different grading strategies as shown in the Factory Pattern.

---

### **6. Implement Authentication and Authorization**

#### **6.1 Configure Spring Security**

- Add `WebSecurityConfig` class.

```java
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/student/**").hasRole("STUDENT")
                .anyRequest().authenticated()
            .and()
            .formLogin()
            .and()
            .httpBasic();
    }
}
```

#### **6.2 Define User Roles**

- Create `User` and `Role` entities.
- Implement `UserDetailsService` for authentication.

```java
@Entity
public class User implements UserDetails {
    @Id
    private String username;
    private String password;
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    // Implement methods from UserDetails interface
}

@Entity
public class Role implements GrantedAuthority {
    @Id
    private String name;

    // Implement methods from GrantedAuthority interface
}
```

#### **6.3 Secure Controllers**

- Use `@PreAuthorize` annotations.

```java
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    // Admin-only endpoints
}

@RestController
@RequestMapping("/api/student")
@PreAuthorize("hasRole('STUDENT')")
public class StudentController {
    // Student-only endpoints
}
```

---

### **7. Advanced Validation and Error Handling**

#### **7.1 Custom Validators**

- Create custom validation annotations.

```java
@Documented
@Constraint(validatedBy = CourseCodeValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCourseCode {
    String message() default "Invalid course code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

public class CourseCodeValidator implements ConstraintValidator<ValidCourseCode, String> {
    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        // Implement validation logic
        return code.matches("^[A-Z]{4}\\d{4}$");
    }
}
```

#### **7.2 Global Exception Handling**

- Implement `@ControllerAdvice`.

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Handle other exceptions
}
```

---

## **Calculations and Business Logic**

### **GPA Calculation**

- GPA is calculated as the weighted average of course grades.
- Assume grades are on a 4.0 scale.

```java
public Double calculateGPA(Long studentId) {
    Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

    Set<Grade> grades = student.getGrades();
    if (grades.isEmpty()) {
        return 0.0;
    }

    double totalPoints = 0.0;
    int totalCredits = 0;

    for (Grade grade : grades) {
        int credits = grade.getCourse().getCredits();
        totalPoints += grade.getScore() * credits;
        totalCredits += credits;
    }

    return totalPoints / totalCredits;
}
```

### **Enrollment Capacity Check**

- Before enrolling a student, check:

```java
if (student.getCourses().size() >= 5) {
    throw new BusinessException("Cannot enroll in more than 5 courses.");
}

if (course.getStudents().size() >= 30) {
    throw new BusinessException("Course capacity reached.");
}
```

---

## **Advanced Topics**

### **Implementing JWT Authentication**

- Instead of basic authentication, use JWT tokens.
- Generate tokens upon successful login.
- Secure APIs using JWT filters.

### **Event-Driven Architecture**

- Use **Spring Events** to decouple components.
- Example: Publish an event when a student enrolls, and handle it to send notifications.

```java
public class EnrollmentEvent extends ApplicationEvent {
    private final Student student;
    private final Course course;

    public EnrollmentEvent(Object source, Student student, Course course) {
        super(source);
        this.student = student;
        this.course = course;
    }

    // Getters
}

@Component
public class EnrollmentListener {
    @EventListener
    public void handleEnrollmentEvent(EnrollmentEvent event) {
        // Send email notification
    }
}
```

### **Caching with Redis**

- Improve performance by caching frequently accessed data.
- Use **Spring Data Redis**.

---

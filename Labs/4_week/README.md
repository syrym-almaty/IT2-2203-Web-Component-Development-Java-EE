# 📝 **Student Task: Implement "Update" Functionality for Student Records**

## 📚 **Objective**
Enhance the existing Student Management System by adding the **"Update"** feature. This will allow users to modify existing student records through the API, completing the basic CRUD (Create, Read, Update, Delete) operations.

## ⏰ **Estimated Time**
**2 Hours**

## 🎯 **Prerequisites**
- Basic understanding of Git and GitHub
- Familiarity with Java, Spring Boot, and RESTful APIs
- Understanding of CRUD operations
- The project repository is **cloned** to your local machine
- Development environment is **set up** and **running** (Java 17, Maven, etc.)

## 📂 **Project Structure Overview**
```
demo
├── .mvn
├── data
│   ├── demo-db.mv.db
│   └── demo-db.trace.db
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── demo
│   │   │               ├── controller
│   │   │               │   ├── DemoController.java
│   │   │               │   └── StudentController.java
│   │   │               ├── entity
│   │   │               │   └── Student.java
│   │   │               ├── repository
│   │   │               │   └── StudentRepository.java
│   │   │               ├── service
│   │   │               │   └── StudentService.java
│   │   │               ├── DemoApplication.java
│   │   │               ├── docker-compose.yml
│   │   │               └── Dockerfile
│   │   └── resources
│   │       ├── application.properties
│   │       └── application.yaml
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── demo
│                       └── DemoApplicationTests.java
├── .gitignore
├── docker-compose.yml
├── Dockerfile
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── reading
    └── mvc
        └── MVC.md
```

---

## 🔧 **Task Breakdown**

1. **Create a New Git Branch**
2. **Implement the Update Endpoint in the Controller**
3. **Enhance the Student Service for Updating Students**
4. **Handle Exceptions Gracefully**
5. **Test the "Update" Functionality**
6. **Commit, Push, and Create a Pull Request**
7. **(Optional) Enhance Functionality for Advanced Students**

---

## 🛠️ **Step-by-Step Instructions**

### 1. Create a New Git Branch

Begin by creating a new branch dedicated to the "Update" functionality. This ensures your work remains organized and does not interfere with the main codebase.

```bash
# Navigate to your project directory
cd path/to/IT2-2204-Web-Component-Development-Java-EE/demo

# Ensure you're on the main branch and it's up to date
git checkout main
git pull origin main

# Create and switch to a new branch
git checkout -b feature/update-student
```

---

### 2. Implement the Update Endpoint in the Controller

Add a new endpoint in the `StudentController` to handle update requests.

- **File Path:** `src/main/java/com/example/demo/controller/StudentController.java`

**Steps:**

1. Open `StudentController.java`.
2. Add the following method to handle `PUT` requests for updating a student:

```java
@Operation(summary = "Update Student", description = "Update an existing student's information")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Student updated successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid input data"),
    @ApiResponse(responseCode = "404", description = "Student not found")
})
@PutMapping("/{id}")
public Student updateStudent(
        @Parameter(description = "UUID of the student to update", required = true)
        @PathVariable UUID id,
        @Parameter(description = "Updated student object", required = true)
        @RequestBody Student updatedStudent) {
    return studentService.updateStudent(id, updatedStudent);
}
```

**Explanation:**
- **@PutMapping("/{id}")**: Maps HTTP PUT requests to this method.
- **@PathVariable UUID id**: Captures the student ID from the URL.
- **@RequestBody Student updatedStudent**: Accepts the updated student data in JSON format.

---

### 3. Enhance the Student Service for Updating Students

Implement the business logic for updating a student in the service layer.

- **File Path:** `src/main/java/com/example/demo/service/StudentService.java`

**Steps:**

1. Open `StudentService.java`.
2. Add the following method to handle the update logic:

```java
public Student updateStudent(UUID id, Student updatedStudent) {
    return studentRepository.findById(id)
            .map(student -> {
                student.setName(updatedStudent.getName());
                student.setEmail(updatedStudent.getEmail());
                // Add other fields as necessary
                return studentRepository.save(student);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
}
```

---

### 4. Handle Exceptions Gracefully

Ensure that your application can handle scenarios where a student is not found.

#### a. Create a Custom Exception

- **File Path:** `src/main/java/com/example/demo/exception/ResourceNotFoundException.java`

**Steps:**

1. Create a new package `exception` inside `com.example.demo`.
2. Add the `ResourceNotFoundException.java` class:

```java
package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

#### b. Implement a Global Exception Handler

- **File Path:** `src/main/java/com/example/demo/exception/GlobalExceptionHandler.java`

**Steps:**

1. Add the `GlobalExceptionHandler.java` class to handle exceptions globally:

```java
package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // You can add more exception handlers here

    // Inner class for error response
    public static class ErrorResponse {
        private int status;
        private String message;
        private LocalDateTime timestamp;

        public ErrorResponse(int status, String message, LocalDateTime timestamp) {
            this.status = status;
            this.message = message;
            this.timestamp = timestamp;
        }

        // Getters and Setters
        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
    }
}
```

**Explanation:**
- **@ControllerAdvice**: Allows handling exceptions globally.
- **handleResourceNotFound**: Returns a structured error response when a `ResourceNotFoundException` is thrown.

---

### 5. Test the "Update" Functionality

Ensure that the "Update" feature works as expected by testing it using **Swagger UI** or **Postman**.

#### a. Start the Application

```bash
# Navigate to the project root directory
cd path/to/IT2-2204-Web-Component-Development-Java-EE/demo

# Use Maven to run the Spring Boot application
./mvnw spring-boot:run
```

> **Note:** On Windows, use `mvnw.cmd` instead of `./mvnw`.

#### b. Access Swagger UI

1. Open your web browser.
2. Navigate to: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

#### c. Update a Student Using Swagger UI

1. **Retrieve an Existing Student:**
   - Use the `GET /api/students` endpoint to fetch the list of students.
   - Note the `UUID` of the student you want to update.

2. **Update the Student:**
   - Use the `PUT /api/students/{id}` endpoint.
   - Click on **"Try it out"**.
   - Enter the `UUID` in the `{id}` field.
   - In the request body, provide the updated student details. For example:

     ```json
     {
       "name": "Jane Doe",
       "email": "jane.doe@example.com"
     }
     ```

   - Click **"Execute"**.
   - Verify that the response status is `200 OK` and the response body reflects the updated information.

3. **Handle Non-Existent Student:**
   - Try updating a student with a random or non-existent `UUID`.
   - Verify that the response status is `404 Not Found` with an appropriate error message.

#### d. Update a Student Using Postman (Alternative Method)

1. Open **Postman**.
2. Create a new `PUT` request to `http://localhost:8080/api/students/{id}`.
3. Replace `{id}` with the actual `UUID` of the student.
4. In the **Body** tab, select **raw** and **JSON** format.
5. Enter the updated student details:

   ```json
   {
     "name": "Jane Doe",
     "email": "jane.doe@example.com"
   }
   ```

6. Click **"Send"**.
7. Verify the response status and body as described above.

---

### 6. Commit, Push, and Create a Pull Request

After successfully implementing and testing the "Update" functionality, commit your changes and create a pull request to merge them into the main branch.

```bash
# Stage all changes
git add .

# Commit with a descriptive message
git commit -m "Implement Update functionality for Student entity"

# Push the branch to GitHub
git push origin feature/update-student
```

#### Create a Pull Request

1. Go to your GitHub repository.
2. You should see a prompt to create a pull request for the recently pushed branch.
3. Click on **"Compare & pull request"**.
4. Add a title and description for your pull request, e.g., "Add Update Endpoint to StudentController".
5. Assign reviewers if necessary.
6. Click **"Create pull request"**.

---

### 7. 🎓 **Optional Enhancements for Advanced Students**

For those who complete the above steps quickly or seek additional challenges, consider implementing the following enhancements:

#### a. Partial Updates with PATCH

- **Objective:** Allow partial updates to student records using the `PATCH` method.
- **Implementation:**
  - Add a `PATCH /api/students/{id}` endpoint.
  - Handle partial updates by checking which fields are provided in the request body.

#### b. Validation Enhancements

- **Objective:** Implement robust validation for incoming data.
- **Implementation:**
  - Use Hibernate Validator annotations (e.g., `@NotNull`, `@Email`, `@Size`) in the `Student` entity.
  - Handle validation errors gracefully and return meaningful error messages.

#### c. Logging

- **Objective:** Add logging to track update operations.
- **Implementation:**
  - Use SLF4J with Logback to log important events, such as successful updates or errors.

#### d. Unit and Integration Tests

- **Objective:** Ensure the update functionality is thoroughly tested.
- **Implementation:**
  - Write unit tests for the `StudentService`.
  - Write integration tests for the `StudentController` using MockMvc.

#### e. Security Enhancements

- **Objective:** Protect the update endpoint from unauthorized access.
- **Implementation:**
  - Implement authentication and authorization using Spring Security.
  - Restrict the `PUT` endpoint to authenticated users with specific roles.

---

## 💡 **Tips for Success**

### For All Students:
- **Read Carefully:** Ensure you understand each step before proceeding.
- **Stay Organized:** Follow the project structure and file paths accurately.
- **Test Frequently:** After implementing each part, test to confirm it works as expected.
- **Seek Help When Needed:** Don’t hesitate to ask peers or instructors if you encounter issues.

### For Advanced Students:
- **Optimize Code:** Refactor for better readability and efficiency.
- **Implement Best Practices:** Follow Spring Boot and RESTful API best practices.
- **Explore Further:** Dive into optional enhancements to deepen your understanding.

---

## 📚 **Resources**

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Documentation](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI Documentation](https://springdoc.org/)
- [Git Branching Basics](https://git-scm.com/book/en/v2/Git-Branching-Basic-Branching-and-Merging)
- [Exception Handling in Spring Boot](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc)

---

## 🎉 **Conclusion**

By completing this task, you will have successfully added the **"Update"** functionality to the Student Management System. This enhancement allows users to modify existing student records, thereby completing the essential CRUD operations within the application.

**Happy Coding!** 🚀

---

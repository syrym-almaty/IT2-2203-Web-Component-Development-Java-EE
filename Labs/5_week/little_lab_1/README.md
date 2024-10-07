# labus

## 📝 **Week 5 Lab: Mastering CRUD Operations with REST APIs**

### 🔍 **Learning Objectives:**

1. **Deepen Understanding of CRUD**: Learn how to implement full CRUD operations (Create, Read, Update, Delete).
2. **REST APIs**: Understand and practice designing RESTful endpoints using Spring Boot.
3. **Error Handling**: Introduce proper error handling in REST APIs.
4. **Testing**: Learn how to write unit and integration tests for API endpoints.
5. **Engagement**: Apply concepts with realistic scenarios and a variety of use cases.

---

### 🔬 **Lab Assignment (Collaborative Work)**

**Title: Advanced Library System with Features for Error Handling and Validation**

#### **Scenario**

In this lab assignment, you will enhance the Library Management System by implementing **data validation**, **error handling**, and **advanced API features**. The aim is to make the API robust and resilient to various scenarios, ensuring it handles both valid and invalid data gracefully.

**🛠️ Task Instructions**:

1. **Add Data Validation**:
    - Use **Java Bean Validation (JSR-380)** with annotations like `@NotBlank`, `@Size`, and `@Pattern` to ensure that books contain valid data.
  
    ```java
    public class Book {
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        private UUID id;

        @NotBlank(message = "Title is mandatory")
        private String title;

        @NotBlank(message = "Author is mandatory")
        private String author;

        @Pattern(regexp = "\\d{13}", message = "ISBN must be 13 digits")
        private String isbn;

        private boolean available;

        // Constructors, Getters, and Setters
    }
    ```

2. **Error Handling with `@ControllerAdvice`**:
    - Create a global exception handler to manage validation and other exceptions.

    ```java
    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    ```

3. **Implement Search Functionality**:
    - Add an endpoint to search for books by **title** or **author**.

    ```java
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String keyword) {
        return bookRepository.findAll().stream()
            .filter(book -> book.getTitle().contains(keyword) || book.getAuthor().contains(keyword))
            .collect(Collectors.toList());
    }
    ```

4. **Testing the API**:
    - Write integration tests for each endpoint using **JUnit** and **MockMvc**.

    ```java
    @SpringBootTest
    @AutoConfigureMockMvc
    public class BookControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        public void testCreateBook() throws Exception {
            String bookJson = "{\"title\": \"Effective Java\", \"author\": \"Joshua Bloch\", \"isbn\": \"9780134685991\", \"available\": true}";

            mockMvc.perform(post("/api/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bookJson))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title").value("Effective Java"));
        }

        @Test
        public void testGetAllBooks() throws Exception {
            mockMvc.perform(get("/api/books"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }
    }
    ```

#### **Lab Study Cases to Try**

1. **Validation Errors**:
   - Send a POST request with missing or incorrect fields, such as an empty title or an invalid ISBN.
   - Observe the error messages returned by the server.

2. **Search for Books**:
   - Implement a search endpoint and test it by querying different titles and authors.
   - Example: `/api/books/search?keyword=Clean` should return any book containing "Clean" in its title or author.

3. **Handle Not Found Exception**:
   - Try to update or delete a book with an incorrect UUID.
   - Make sure the error handler returns a **404** with a meaningful message.

4. **Integration Testing**:
   - Write and execute integration tests for each endpoint.
   - Test edge cases, like searching with an empty keyword or deleting books that are referenced in another entity.

5. **Collaborative Scenario**:
   - Instruct students to collaboratively extend the library system by adding features such as:
     - **Genre categorization**.
     - **Borrowing and return operations**.
     - Implementing **authorization** to restrict some actions to "admin" users only.

---

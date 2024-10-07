# practicus

## 📝 **Week 5 Lab/practice: Mastering CRUD Operations with REST APIs**

### 🔍 **Learning Objectives:**

1. **Deepen Understanding of CRUD**: Learn how to implement full CRUD operations (Create, Read, Update, Delete).
2. **REST APIs**: Understand and practice designing RESTful endpoints using Spring Boot.
3. **Error Handling**: Introduce proper error handling in REST APIs.
4. **Testing**: Learn how to write unit and integration tests for API endpoints.
5. **Engagement**: Apply concepts with realistic scenarios and a variety of use cases.

---

### 📑 **Practice Task (Independent Work)**

**Title: Building CRUD for a Simple Library System**

#### **Scenario**

You're tasked with building a simple REST API for a Library Management System. The system will manage information about books, such as the **title**, **author**, **ISBN**, and **availability** status.

**📋 Task Instructions:**

1. **Create a Book Entity**:
    - Fields:
      - `UUID id` (auto-generated)
      - `String title`
      - `String author`
      - `String isbn`
      - `boolean available`

    ```java
    @Entity
    public class Book {
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        private UUID id;

        private String title;
        private String author;
        private String isbn;
        private boolean available;

        // Constructors, Getters, and Setters
    }
    ```

2. **Create BookRepository**:
    - Extend `JpaRepository` to manage Book entities.

    ```java
    @Repository
    public interface BookRepository extends JpaRepository<Book, UUID> {
    }
    ```

3. **Implement BookService**:
    - Service to manage book CRUD operations.

    ```java
    @Service
    public class BookService {
        @Autowired
        private BookRepository bookRepository;

        public List<Book> getAllBooks() {
            return bookRepository.findAll();
        }

        public Book createBook(Book book) {
            return bookRepository.save(book);
        }

        public Book getBookById(UUID id) {
            return bookRepository.findById(id).orElse(null);
        }

        public Book updateBook(UUID id, Book bookDetails) {
            Book book = bookRepository.findById(id).orElse(null);
            if (book != null) {
                book.setTitle(bookDetails.getTitle());
                book.setAuthor(bookDetails.getAuthor());
                book.setIsbn(bookDetails.getIsbn());
                book.setAvailable(bookDetails.isAvailable());
                return bookRepository.save(book);
            }
            return null;
        }

        public void deleteBook(UUID id) {
            bookRepository.deleteById(id);
        }
    }
    ```

4. **Create BookController**:
    - REST controller for managing books.

    ```java
    @RestController
    @RequestMapping("/api/books")
    public class BookController {
        @Autowired
        private BookService bookService;

        @GetMapping
        public List<Book> getAllBooks() {
            return bookService.getAllBooks();
        }

        @PostMapping
        public Book createBook(@RequestBody Book book) {
            return bookService.createBook(book);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Book> getBookById(@PathVariable UUID id) {
            Book book = bookService.getBookById(id);
            if (book != null) {
                return ResponseEntity.ok(book);
            }
            return ResponseEntity.notFound().build();
        }

        @PutMapping("/{id}")
        public ResponseEntity<Book> updateBook(@PathVariable UUID id, @RequestBody Book bookDetails) {
            Book updatedBook = bookService.updateBook(id, bookDetails);
            if (updatedBook != null) {
                return ResponseEntity.ok(updatedBook);
            }
            return ResponseEntity.notFound().build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        }
    }
    ```

#### **Practice Cases to Try**

1. **Add a New Book**:
   - Send a POST request to `/api/books` with a new book's details.
   - Example Request:

     ```json
     {
       "title": "Clean Code",
       "author": "Robert C. Martin",
       "isbn": "9780132350884",
       "available": true
     }
     ```

2. **Retrieve All Books**:
   - Send a GET request to `/api/books` to get a list of all books.

3. **Update a Book's Availability**:
   - Update the availability status of a book by sending a PUT request to `/api/books/{id}`.

4. **Delete a Book**:
   - Send a DELETE request to `/api/books/{id}` to remove a book from the system.

5. **Error Handling**:
   - Try to access a non-existent book using `/api/books/{non-existent-id}` and observe the 404 response.

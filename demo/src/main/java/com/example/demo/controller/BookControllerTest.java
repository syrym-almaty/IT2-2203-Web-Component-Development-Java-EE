package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Book book;

    @BeforeEach
    public void setup() {
        bookRepository.deleteAll();
        book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setIsbn("9780134685991");
        book.setAvailable(true);
        book = bookRepository.save(book);
    }

    @Test
    public void testCreateBook() throws Exception {
        Book newBook = new Book();
        newBook.setTitle("Clean Code");
        newBook.setAuthor("Robert C. Martin");
        newBook.setIsbn("9780132350884");
        newBook.setAvailable(true);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Clean Code"));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Effective Java"));
    }

    @Test
    public void testGetBookById() throws Exception {
        mockMvc.perform(get("/api/books/" + book.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Effective Java"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        book.setTitle("Effective Java, 3rd Edition");

        mockMvc.perform(put("/api/books/" + book.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Effective Java, 3rd Edition"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/api/books/" + book.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/books/" + book.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSearchBooks() throws Exception {
        mockMvc.perform(get("/api/books/search")
                        .param("keyword", "Effective"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].author").value("Joshua Bloch"));
    }

    @Test
    public void testCreateBookValidationFailure() throws Exception {
        Book invalidBook = new Book();
        invalidBook.setTitle("");
        invalidBook.setAuthor("");
        invalidBook.setIsbn("123"); // Invalid ISBN

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidBook)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Title is mandatory"))
                .andExpect(jsonPath("$.author").value("Author is mandatory"))
                .andExpect(jsonPath("$.isbn").value("ISBN must be 13 digits"));
    }
}

package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam  @NotBlank(message = "Keyword is mandatory") String keyword) {
        return bookService.getBookRepository().findAll().stream()
                .filter(book -> book.getTitle().contains(keyword) || book.getAuthor().contains(keyword))
                .collect(Collectors.toList());
    }
    @PostMapping("/add_book")
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }
}

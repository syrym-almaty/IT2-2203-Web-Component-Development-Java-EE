package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
@Autowired
@Getter
BookRepository bookRepository;

public void addBook(Book book){
    bookRepository.save(book);
}


}

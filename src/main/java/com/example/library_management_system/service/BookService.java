package com.example.library_management_system.service;


import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.Member;
import com.example.library_management_system.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Book create(Book book)
    {
        return bookRepository.save(book);
    }

    public List<Book> findAll()
    {
        return bookRepository.findAll();
    }


    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    public Book findById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void updateBook(Book book) {
        bookRepository.save(book); // Save method automatically update kore jodi ID thake
    }



}

package com.example.library_management_system.service;


import com.example.library_management_system.model.Authors;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void save(Authors authors)
    {
        authorRepository.save(authors);
    }

    public List<Authors> findAll()
    {
        return authorRepository.findAll();
    }

    public Authors findById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    public void updateAuthor(Authors authors) {
        authorRepository.save(authors); // Save method automatically update kore jodi ID thake
    }

}

package com.example.library_management_system.repository;

import com.example.library_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    void deleteById(int id);

}

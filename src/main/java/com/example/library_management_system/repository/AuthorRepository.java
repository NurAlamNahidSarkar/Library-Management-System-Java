package com.example.library_management_system.repository;


import com.example.library_management_system.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Authors, Integer> {
}

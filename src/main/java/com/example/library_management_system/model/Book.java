package com.example.library_management_system.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String ISBN;
    private int numberBooks;



    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author", // join table name
            joinColumns = @JoinColumn(name = "book_id"), // this entity's FK
            inverseJoinColumns = @JoinColumn(name = "author_id") // other entity's FK
    )
    private List<Authors> authors;


    @ManyToMany(mappedBy = "assignedBooks")
    private List<Member> assignedMembers;


    public Book(List<Authors> authors) {
        this.authors = authors;
    }

    public List<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Authors> authors) {
        this.authors = authors;
    }

    public Book() {

    }

    public Book(int id, String title, String ISBN, int numberBooks) {
        this.id = id;
        this.title = title;
        this.ISBN = ISBN;
        this.numberBooks = numberBooks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getNumberBooks() {
        return numberBooks;
    }

    public void setNumberBooks(int numberBooks) {
        this.numberBooks = numberBooks;
    }
}

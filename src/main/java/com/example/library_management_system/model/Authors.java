package com.example.library_management_system.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;
    private String authorName;


    @ManyToMany(mappedBy = "authors")
    private List<Book> books;


    public Authors() {

    }


    public Authors(int authorId, String authorName) {
        this.authorId = authorId;
        this.authorName = authorName;
    }


    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}

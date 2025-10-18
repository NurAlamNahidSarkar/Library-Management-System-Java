package com.example.library_management_system.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone_number;
    private String address;
    private String password;


    @ManyToMany
    @JoinTable(
            name = "member_book", // intermediate table name
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> assignedBooks;

    public List<Book> getAssignedBooks() {
        return assignedBooks;
    }

    public void setAssignedBooks(List<Book> assignedBooks) {
        this.assignedBooks = assignedBooks;
    }

    public Member() {

    }

    public Member(int id, String name,String email, String phone_number, String address,String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.example.library_management_system.controller;


import com.example.library_management_system.model.Authors;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.Member;
import com.example.library_management_system.service.AuthorService;
import com.example.library_management_system.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;


    public BookController(BookService bookService,AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }


    @GetMapping("/addBook")
    public String addBook(Model model)
    {
        model.addAttribute("book",new Book());
        model.addAttribute("authors",new Authors());
        return "addBook";
    }


    @PostMapping("/addBook")
    public String saveBook(@ModelAttribute Book book,@ModelAttribute Authors authors, Model model)
    {
        bookService.create(book);
        authorService.save(authors);
        model.addAttribute("successMessage", "Book Added successfully!");
        return "addBook";  // Redirect to avoid resubmitting the form if page is refreshed
    }


    @GetMapping("/bookList")
    public String bookList(Model model)
    {
        List<Book> books = bookService.findAll();
        //List<Authors> authors = authorService.findAll();
        model.addAttribute("bookList", books);
        //model.addAttribute("authorList", authors);
        return "bookList";
    }


    @GetMapping("/removeBook")
    public String removeBook(Model model)
    {
        List<Book> books = bookService.findAll();
        model.addAttribute("bookList", books);
        return "removeBook";
    }


    @GetMapping("/removeBook/delete/{id}")
    public String removeBook(@PathVariable int id)
    {
        bookService.deleteById(id);
        return "redirect:/removeBook";
    }


    @GetMapping("/updateBook")
    public String updateBook(Model model)
    {
        List<Book> books = bookService.findAll();
        model.addAttribute("bookList", books);
        return "updateBook";
    }



    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable int id, Model model) {
        Book book = bookService.findById(id);// Service layer theke ekta Book niye ashbo
        Authors authors = authorService.findById(id);
        model.addAttribute("book", book);     // "book" naam e model e pathabo
        model.addAttribute("authors",authors);
        return "editBook"; // editBook.html file load hobe
    }

    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute Book book, @ModelAttribute Authors authors, RedirectAttributes redirectAttributes) {
        bookService.updateBook(book);
        authorService.updateAuthor(authors);
        redirectAttributes.addFlashAttribute("successMessage", "Book Updated successfully!");
        return "redirect:/bookList";
    }

}

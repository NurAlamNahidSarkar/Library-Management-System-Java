package com.example.library_management_system.controller;

import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.Member;
import com.example.library_management_system.service.BookService;
import com.example.library_management_system.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final BookService bookService;

    public MemberController(MemberService memberService,BookService bookService) {
        this.memberService = memberService;
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String loginPage(Model model)
    {
        model.addAttribute("member", new Member()); // empty form object
        return "loginMember";
    }

    @PostMapping("/loginSubmit")
    public String loginSubmit(@ModelAttribute Member member, Model model) {
        Member foundMember = memberService.getByName(member.getName());

        if (foundMember != null) {
            System.out.println("Found member: " + foundMember.getName());

            model.addAttribute("member", foundMember); // Full Member object send korchi

            return "memberDashboard"; // Success -> dashboard
        } else {
            System.out.println("Invalid login attempt.");
            model.addAttribute("error", "Invalid name or password");
            model.addAttribute("member", new Member()); // empty form abar dibe
            return "loginMember"; // Failure -> login page e thakbe
        }
    }



    @GetMapping("/reg")
    public String registrationPage(Model model)
    {
        model.addAttribute("member",new Member());
        return "registrationMember";
    }

    @PostMapping("/regSubmit")
    public String regSubmit(@ModelAttribute Member member, Model model) {
        memberService.create(member);
        model.addAttribute("member", member); // full object pathacchi
        return "memberDashboard";
    }


    @GetMapping("/viewMember/{name}")
    public String viewMember(@PathVariable String name, Model model) {
        Member member = memberService.findByName(name);
        model.addAttribute("member",member);
        return "viewMember";
    }


    @GetMapping("/editViewMember/{id}")
    public String editViewMember(@PathVariable int id, Model model)
    {
        Member member = memberService.findById(id);
        model.addAttribute("member",member);
        return "editViewMember";
    }

    @PostMapping("/updateViewMember")
    public String updateViewMember(@ModelAttribute Member member,Model model) {
        memberService.updateMember(member);
        model.addAttribute("successMessage", "Member information updated successfully!");
        return "viewMember"; // or wherever you want to go after update
    }


    //Book Manage


    @GetMapping("/memberBook")
    public String memberBookList(Model model)
    {
        List<Book> books = bookService.findAll();
        model.addAttribute("bookList", books);
        return "memberBook";
    }

    @GetMapping("/availableBooks")
    public String availableBooks(Model model)
    {
        List<Book> books = bookService.findAll();
        model.addAttribute("bookList", books);
        return "availableBooks";
    }








}

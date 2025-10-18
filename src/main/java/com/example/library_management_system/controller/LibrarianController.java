package com.example.library_management_system.controller;

import com.example.library_management_system.model.Authors;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.Librarian;
import com.example.library_management_system.model.Member;
import com.example.library_management_system.service.BookService;
import com.example.library_management_system.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
public class LibrarianController {

    private final MemberService memberService;
    private final BookService bookService;

    public LibrarianController(MemberService memberService,BookService bookService) {
        this.memberService = memberService;
        this.bookService = bookService;
    }

    @GetMapping("/librarianLogin")
    public String librarianLoginPage(Model model)
    {
        model.addAttribute("librarian", new Librarian());
        return "librarianLogin";
    }


    @PostMapping("/librarianLoginSubmit")
    public String librarianLoginSubmit(@ModelAttribute Librarian librarian, Model model)
    {
        model.addAttribute("name", librarian.getName());
        model.addAttribute("password", librarian.getPassword());

        String validName = "Nur Alam Nahid";
        String validPassword = "2220";

        if (librarian.getName().equals(validName) && librarian.getPassword().equals(validPassword)) {
            model.addAttribute("name", librarian.getName());
            return "librarianDashboard"; // only if valid name and password
        } else {
            model.addAttribute("error", "Invalid name or password");
            return "librarianLogin"; // return back to login page
        }
    }


    @GetMapping("/memberRecruitment")
    public String memberRecruitment(Model model)
    {
        model.addAttribute("member", new Member());
        return "memberRecruitment";
    }

    @PostMapping("/memberRecruitment")
    public String saveMemberRecruitment(@ModelAttribute Member member, Model model)
    {
        model.addAttribute("name", member.getName());
        model.addAttribute("email", member.getEmail());
        model.addAttribute("phone_number", member.getPhone_number());
        model.addAttribute("address", member.getAddress());
        model.addAttribute("password", member.getPassword());
        memberService.create(member);
        model.addAttribute("successMessage", "Member recruitment was successful!");
        return "memberRecruitment";
    }


    @GetMapping("/memberList")
    public String memberList(Model model)
    {
        List<Member> members = memberService.findAll();
        model.addAttribute("memberList", members);
        return "memberList";
    }


    @GetMapping("/editMemberInfo")
    public String editMemberInfo(Model model)
    {
        List<Member> members = memberService.findAll();
        model.addAttribute("memberList", members);
        return "editMemberInfo";
    }

    @GetMapping("/editMemberInfo/delete/{id}")
    public String deleteMember(@PathVariable int id) {
        memberService.deleteById(id);
        return "redirect:/editMemberInfo";
    }

    @GetMapping("/editMember/{id}")
    public String editMember(@PathVariable int id,Model model)
    {
        Member member = memberService.findById(id);
        model.addAttribute("member", member);
        return "editMember";
    }

    @PostMapping("/updateMember")
    public String updateMember(@ModelAttribute Member member,RedirectAttributes redirectAttributes) {
        memberService.updateMember(member);
        redirectAttributes.addFlashAttribute("successMessage", "Member Info Updated successfully!");
        return "redirect:/editMemberInfo"; // or wherever you want to go after update
    }



    @GetMapping("/assignBooks")
    public String assignBooks(Model model)
    {
        List<Member> members = memberService.findAll();
        model.addAttribute("memberList", members);
        return "assignBooks";
    }


    @GetMapping("/assignBooksToMember/{id}")
    public String assignBooksToMember(@PathVariable int id,Model model)
    {
        Member member = memberService.findById(id);
        List<Book> bookList = bookService.findAll();
        model.addAttribute("member",member);
        model.addAttribute("bookList", bookList);
        return "assignBooksToMember";
    }

    @PostMapping("/saveAssignBook")
    public String saveAssignBooks(@RequestParam("id") int memberId, @RequestParam("bookId") int bookId, Model model)
    {
        memberService.assignBookToMember(memberId, bookId);
        return "viewMember";
    }














}

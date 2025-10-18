package com.example.library_management_system.service;


import com.example.library_management_system.model.Authors;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.Member;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public MemberService(MemberRepository memberRepository,BookRepository bookRepository) {
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    public Member create(Member member)
    {
        return memberRepository.save(member);
    }



    public Member getByName(String name)
    {
        Optional<Member> optional = memberRepository.getByName(name);
        return optional.orElse(null);
    }

    public Member getById(int id)
    {
        Optional<Member> optional = memberRepository.getById(id);
        return optional.orElse(null);
    }

    public List<Member> findAll()
    {
        return memberRepository.findAll();
    }

    public void deleteById(int id) {
        memberRepository.deleteById(id);
    }


    public Member findById(int id) {
        return memberRepository.findById(id);
    }


    public Member findByName(String Name) {
        return memberRepository.findByName(Name);
    }


    public void updateMember(Member member) {
        memberRepository.save(member); // Save method automatically update kore jodi ID thake
    }

    public void assignBookToMember(int memberId, int bookId) {
        // Fetch member and book from database
        Member member = memberRepository.findById(memberId);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        // Assign book to member
        member.getAssignedBooks().add(book);

        // Save updated member
        memberRepository.save(member);
    }


}

package com.example.library_management_system.repository;

import com.example.library_management_system.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> getByName(String name);
    Optional<Member> getById(int id);
    Member findById(int id);
    Member findByName(String name);

    void deleteById(int id);



    //void findById(int id);




}

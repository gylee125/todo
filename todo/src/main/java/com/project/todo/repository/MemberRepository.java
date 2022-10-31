package com.project.todo.repository;

import com.project.todo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{

    Member getMemberByEmail(String email);

}

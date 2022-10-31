package com.project.todo.service;

import com.project.todo.dto.UserRole;
import com.project.todo.entity.Member;
import com.project.todo.repository.MemberRepository;
import com.project.todo.repository.TodoRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;
    private final PasswordEncoder passwordEncoder;

    public Member register(String email, String password, String name) {
        Member member = new Member();

        member.setEmail(email);
        member.setPassword(passwordEncoder.encode(password));
        member.setName(name);
        member.setIsAdmin(false);

        this.memberRepository.save(member);
        return member;
    }

   public Member chkMember(String email) {
       return validateDuplicateMember(email);
    }

    private Member validateDuplicateMember(String email) {
        Member findMember = memberRepository.getMemberByEmail(email);
        return findMember;
    }

    public Member getUser(String username){
        Member member = this.memberRepository.getMemberByEmail(username);

        return member;

    }

    @Transactional
    public void removeWithTodos(Long mno) {

        todoRepository.deleteByMno(mno);

        memberRepository.deleteById(mno);
    }


}

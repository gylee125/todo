package com.project.todo.service;

import com.project.todo.dto.LoginMemberDetails;
import com.project.todo.entity.Member;
import com.project.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public LoginMemberDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.getMemberByEmail(email);
        if (member == null) {
            System.out.println("member x");
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        LoginMemberDetails loginMemberDetails = new LoginMemberDetails();
        loginMemberDetails.setMno(member.getMno());
        loginMemberDetails.setEmail(member.getEmail());
        loginMemberDetails.setPassword(member.getPassword());
        loginMemberDetails.setName(member.getName());
        loginMemberDetails.setIsAdmin(member.getIsAdmin());

        return loginMemberDetails;
    }
}

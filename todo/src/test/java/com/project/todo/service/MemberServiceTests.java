package com.project.todo.service;

import com.project.todo.entity.Member;
import com.project.todo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberServiceTests {

    @Autowired
    private MemberService memberService;


    //@Test
    /*
    public void testRegister() {
        IntStream.rangeClosed(1, 50).forEach(i -> {

            Member member = new Member();

            member.setEmail("user" + i + "@aaa.com");
            member.setPassword(passwordEncoder.encode("1111"));
            member.setName("USER" + i);
            member.setIsAdmin(false);

            this.memberRepository.save(member);
            this.m

        });
    }
     */
}

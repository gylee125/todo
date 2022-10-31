package com.project.todo.repository;

import com.project.todo.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertAdmin() {

        Member member = Member.builder()
                .email("admin@aaa.com")
                .password("admin")
                .name("ADMIN")
                .isAdmin(true)
                .build();

        memberRepository.save(member);
    }

    @Test
    public void insertMembers() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            Member member = Member.builder()
                    .email("user" + i + "@aaa.com")
                    .password("1111")
                    .name("USER" + i)
                    .isAdmin(false)
                    .build();

            memberRepository.save(member);
        });
    }

    @Test
    public void deleteAll() {
        memberRepository.deleteAll();

    }


}

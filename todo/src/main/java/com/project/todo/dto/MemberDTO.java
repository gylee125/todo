package com.project.todo.dto;

import com.project.todo.entity.Member;

public class MemberDTO {

    private Long mno;
    private String email;
    private String password;
    private String name;
    private Boolean isAdmin;

    public Member toEntity() {
        Member member = Member.builder()
                .mno(mno)
                .email(email)
                .password(password)
                .name(name)
                .isAdmin(isAdmin)
                .build();
        return member;
    }
}

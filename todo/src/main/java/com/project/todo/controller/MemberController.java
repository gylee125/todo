package com.project.todo.controller;

import com.project.todo.dto.MemberFormDTO;
import com.project.todo.entity.Member;
import com.project.todo.service.MemberService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/register")
    public void register(Model model) {
        model.addAttribute("memberFormDTO", new MemberFormDTO());
    }

    @PostMapping("/register")
    public String register(@Valid MemberFormDTO memberFormDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member_register";
        }

        if (!memberFormDTO.getPassword1().equals(memberFormDTO.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "*2개의 패스워드가 일치하지 않습니다.");
            return "member_register";
        }

        if (memberService.chkMember(memberFormDTO.getEmail()) != null) {
            bindingResult.rejectValue("email", "emailInValid",
                    "*이미 등록된 이메일입니다.");
            return "member_register";
        }

        try {
            memberService.register(memberFormDTO.getEmail(), memberFormDTO.getPassword1(), memberFormDTO.getName());
        } catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "member_register";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public void login() {
    }

}

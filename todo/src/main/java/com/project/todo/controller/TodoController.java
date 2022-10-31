package com.project.todo.controller;

import com.project.todo.dto.LoginMemberDetails;
import com.project.todo.dto.TodoDTO;
import com.project.todo.entity.Member;
import com.project.todo.service.MemberService;
import com.project.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Log4j2
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;
    private final MemberService memberService;

    @GetMapping(value = "/list")
    public void getlistPage(Model model, @AuthenticationPrincipal LoginMemberDetails loginMemberDetails) {
        if(loginMemberDetails == null){
            model.addAttribute("msg", "로그인이 필요합니다.");
        } else {
            System.out.println("login success: " + loginMemberDetails.getMno());
            model.addAttribute("loginMember", loginMemberDetails );
        }

    }

    @ResponseBody
    @GetMapping(value = "/list/{mno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TodoDTO>> getlistByMember(@PathVariable("mno") Long mno ) {

        log.info("mno: " + mno);
        return new ResponseEntity<>( todoService.getList(mno), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/list/{mno}/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TodoDTO>> getActivelistByMember(@PathVariable("mno") Long mno ) {

        log.info("mno: " + mno);
        return new ResponseEntity<>( todoService.getActiveList(mno), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/list/{mno}/completed", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TodoDTO>> getCompletedlistByMember(@PathVariable("mno") Long mno ) {

        log.info("mno: " + mno);
        return new ResponseEntity<>( todoService.getCompletedList(mno), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> register(@RequestBody TodoDTO todoDTO){

        log.info(todoDTO);

        Long tno = todoService.register(todoDTO);

        return new ResponseEntity<>(tno, HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/{tno}")
    public ResponseEntity<String> remove(@PathVariable("tno") Long tno) {

        log.info("tno: "+ tno);

        todoService.remove(tno);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/{tno}")
    public ResponseEntity<String> modify(@RequestBody TodoDTO todoDTO){

        log.info(todoDTO);

        todoService.modify(todoDTO);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/{tno}/completeSwitch")
    public ResponseEntity<String> modifyComplted(@RequestBody TodoDTO todoDTO){

        log.info(todoDTO);

        todoService.modify(todoDTO);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}

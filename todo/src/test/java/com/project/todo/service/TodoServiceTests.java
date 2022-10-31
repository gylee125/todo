package com.project.todo.service;

import com.project.todo.dto.TodoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    public void testGetList() {

        Long mno = 100L;

        List<TodoDTO> todoDTOList = todoService.getList(mno);

        todoDTOList.forEach(todoDTO -> System.out.println(todoDTO));
    }

    @Test
    public void testGetCompletedList() {

        Long mno = 158L;

        List<TodoDTO> todoDTOList = todoService.getCompletedList(mno);

        todoDTOList.forEach(todoDTO -> System.out.println(todoDTO));
    }
}

package com.project.todo.service;

import com.project.todo.dto.TodoDTO;
import com.project.todo.entity.Member;
import com.project.todo.entity.Todo;

import java.util.List;

public interface TodoService {

    Long register(TodoDTO todoDTO);

    List<TodoDTO> getList(Long mno);

    List<TodoDTO> getActiveList(Long mno);

    List<TodoDTO> getCompletedList(Long mno);


    void modify(TodoDTO todoDTO);

    void remove(Long tno);

    // TodoDTO -> Todo entity
    default Todo dtoToEntity(TodoDTO todoDTO) {

        Member member = Member.builder().mno(todoDTO.getMno()).build();

        Todo todo = Todo.builder()
                .tno(todoDTO.getTno())
                .content(todoDTO.getContent())
                .user(member)
                .completed(todoDTO.getCompleted())
                .build();

        return todo;
    }

    // Todo entity -> TodoDTO
    default TodoDTO entityToDTO(Todo todo){

        TodoDTO dto = TodoDTO.builder()
                .tno(todo.getTno())
                .content(todo.getContent())
                .completed(todo.getCompleted())
                .build();

        return dto;
    }
}

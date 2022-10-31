package com.project.todo.service;

import com.project.todo.dto.TodoDTO;
import com.project.todo.entity.Member;
import com.project.todo.entity.Todo;
import com.project.todo.repository.MemberRepository;
import com.project.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;


    @Override
    public Long register(TodoDTO todoDTO) {

        Todo todo = dtoToEntity(todoDTO);

        todoRepository.save(todo);

        return todo.getTno();
    }

    @Override
    public List<TodoDTO> getList(Long mno) {

        List<Todo> result = todoRepository.getTodosByUserOrderByTno(Member.builder().mno(mno).build());

        return result.stream().map(todo -> entityToDTO(todo)).collect(Collectors.toList());
    }

    @Override
    public List<TodoDTO> getCompletedList(Long mno) {

        List<Todo> result = todoRepository.getTodosByUserAndCompletedOrderByTno(Member.builder().mno(mno).build(), true);

        return result.stream().map(todo -> entityToDTO(todo)).collect(Collectors.toList());
    };

    @Override
    public List<TodoDTO> getActiveList(Long mno) {
        List<Todo> result = todoRepository.getTodosByUserAndCompletedOrderByTno(Member.builder().mno(mno).build(), false);

        return result.stream().map(todo -> entityToDTO(todo)).collect(Collectors.toList());
    };

    @Override
    public void modify(TodoDTO todoDTO) {

        Todo todo = dtoToEntity(todoDTO);

        todoRepository.save(todo);
    }

    @Override
    public void remove(Long tno) {

        todoRepository.deleteById(tno);
    }
}

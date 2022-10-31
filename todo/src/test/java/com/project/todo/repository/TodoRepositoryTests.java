package com.project.todo.repository;

import com.project.todo.entity.Member;
import com.project.todo.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void insertToDo() {

        IntStream.rangeClosed(1, 20).forEach(i -> {

            Member member = Member.builder().mno(162L).build();

            Todo todo = Todo.builder()
                    .content("공부"+i)
                    .user(member)
                    .completed(false)
                    .build();

            todoRepository.save(todo);
        });
    }

    @Transactional
    @Test
    public void readTodo1() {
        Optional<Todo> result = todoRepository.findById(20L);
        Todo todo = result.get();
        System.out.println(todo);
        System.out.println(todo.getUser());
    }

    @Test
    public void testListByUser() {

        List<Todo> todoList = todoRepository.getTodosByUserOrderByTno(
                Member.builder().mno(100L).build());

        todoList.forEach(todo -> System.out.println(todo));
    }


    @Test
    public void deleteAll() {
        todoRepository.deleteAll();
    }
}

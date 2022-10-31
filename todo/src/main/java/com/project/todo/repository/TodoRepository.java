package com.project.todo.repository;

import com.project.todo.entity.Member;
import com.project.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("select t, u from Todo t left join t.user u where t.tno =:tno")
    Object getTodoWithUser(@Param("tno") Long tno);

    @Modifying
    @Query("delete from Todo t where t.user.mno =:mno ")
    void deleteByMno(Long mno);

    List<Todo> getTodosByUserOrderByTno(Member user);

    List<Todo> getTodosByUserAndCompletedOrderByTno(Member user, Boolean completed);

}

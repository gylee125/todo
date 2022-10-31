package com.project.todo.repository;

import com.project.todo.entity.Board;
import com.project.todo.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoard() {

        IntStream.rangeClosed(1,5).forEach(i -> {

            Member member = Member.builder().mno(51L).build();

            Board board = Board.builder()
                    .title("[NOTICE]"+i)
                    .content("Content..."+i)
                    .writer(member)
                    .build();

            boardRepository.save(board);

        });
    }

    @Transactional
    @Test
    public void testRead1() {
        Optional<Board> result = boardRepository.findById(55L); //데이터베이스에 존재하는 번호
        Board board = result.get();
        System.out.println(board);
        System.out.println(board.getWriter());
    }


    @Test
    public void deleteAll() {
        boardRepository.deleteAll();
    }
}

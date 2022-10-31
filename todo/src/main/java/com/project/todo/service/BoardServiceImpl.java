package com.project.todo.service;

import com.project.todo.dto.BoardDTO;
import com.project.todo.dto.PageRequestDTO;
import com.project.todo.dto.PageResultDTO;
import com.project.todo.entity.Board;
import com.project.todo.entity.Member;
import com.project.todo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;


    @Override
    public Long register(BoardDTO dto) {
        log.info(dto);
        Board board = dtoToEntity(dto);
        boardRepository.save(board);

        return board.getBno();
    }

    /*
    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);
        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board)en[0],(Member)en[1]));

        Page<Object[]> result = boardRepository.getBoardsOrderByBno(
            pageRequestDTO.getPageable(Sort.by("bno").descending()) );

        return new PageResultDTO<>(result, fn);
    }
     */

    @Override
    public BoardDTO get(Long bno) {
        Object result = boardRepository.getBoardWithWriter(bno);
        Object[] arr = (Object[])result;

        return entityToDTO((Board)arr[0], (Member)arr[1]);
    }

    @Transactional
    @Override
    public void modify(BoardDTO boardDTO) {

        Board board = boardRepository.getReferenceById(boardDTO.getBno());
        if(board != null) {
            board.changeTitle(boardDTO.getTitle());
            board.changeContent(boardDTO.getContent());
            boardRepository.save(board);
        }
    }
}

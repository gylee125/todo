package com.project.todo.service;

import com.project.todo.dto.BoardDTO;
import com.project.todo.dto.PageRequestDTO;
import com.project.todo.dto.PageResultDTO;
import com.project.todo.entity.Board;
import com.project.todo.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    //PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long bno);

    void modify(BoardDTO boardDTO);

    default Board dtoToEntity(BoardDTO dto){
        Member member = Member.builder().mno(dto.getMno()).build();
        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }

    default BoardDTO entityToDTO(Board board, Member member) {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .mno(member.getMno())
                .writerName(member.getName())
                .build();
        return boardDTO;
    }
}

package com.project.todo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long bno;
    private String title;
    private String content;
    private Long mno;
    private String writerName; //작성자의 이름
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}

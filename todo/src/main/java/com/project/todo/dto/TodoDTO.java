package com.project.todo.dto;

import com.project.todo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoDTO {

    private Long tno;
    private String content;
    private Long mno;
    private Boolean completed;
}

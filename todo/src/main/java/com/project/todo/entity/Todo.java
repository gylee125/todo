package com.project.todo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "user")
public class Todo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    @Column(nullable = false)
    private String content;

    @ManyToOne (fetch = FetchType.LAZY)
    private Member user;

    private Boolean completed;

    public void changeContent(String content){
        this.content = content;
    }
}

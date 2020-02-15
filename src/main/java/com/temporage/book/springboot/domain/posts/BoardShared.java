package com.temporage.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "shared_board")
public class BoardShared {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column
    private int boardId;

    @Column
    private int userId;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    @CreationTimestamp
    LocalDateTime shared_date;

    @Builder
    public BoardShared(int boardId, int userId){
        this.boardId=boardId;
        this.userId=userId;
    }
}

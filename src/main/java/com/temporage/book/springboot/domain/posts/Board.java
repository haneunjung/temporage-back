package com.temporage.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String categoryId;

    @Column(length = 256, nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(length = 64, nullable = false)
    private String email;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate create_date;

    @Builder
    public Board(String title, String contents, String email, String categoryId){
        this.title = title;
        this.contents = contents;
        this.email = email;
        this.categoryId = categoryId;
    }

}

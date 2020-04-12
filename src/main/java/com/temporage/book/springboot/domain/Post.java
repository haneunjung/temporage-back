package com.temporage.book.springboot.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_idx")
    private int postIdx;

    @ManyToOne
    @JoinColumn(name = "category_idx", nullable = false)
    private Category categoryIdx = new Category();

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    private UserInfo userIdx = new UserInfo();

    @Column(name = "post_contents", nullable = false)
    private String postContents;

    @Column(name = "create_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    @CreationTimestamp
    private LocalDate create_date;

    @Builder
    public Post(String postContents, Category categoryIdx, UserInfo userIdx){
        this.postContents = postContents;
        this.categoryIdx = categoryIdx;
        this.userIdx = userIdx;
    }

}

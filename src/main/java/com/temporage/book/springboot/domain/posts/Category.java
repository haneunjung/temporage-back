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
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "category_name", length = 128, nullable = false)
    private String category_name;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate create_date;

    @Builder
    public Category(String category_name){
        this.category_name = category_name;
    }

}

package com.temporage.book.springboot.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_idx", nullable = false)
    private int categoryIdx;

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    private UserInfo userIdx = new UserInfo();

    @Column(name = "category_name", length = 128, nullable = false)
    private String categoryName;

    @Builder
    public Category(String category_name, UserInfo userIdx) {
        this.categoryName = category_name;
        this.userIdx = userIdx;
    }

}

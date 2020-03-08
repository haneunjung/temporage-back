package com.temporage.book.springboot.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "shared_board")
public class PostShared {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int sharedPostIdx;

    @ManyToOne
    @JoinColumn(name = "post_idx")
    private Post postIdx = new Post();

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private UserInfo userIdx = new UserInfo();

    @Builder
    public PostShared(Post postIdx, UserInfo userIdx){
        this.postIdx = postIdx;
        this.userIdx = userIdx;
    }
}

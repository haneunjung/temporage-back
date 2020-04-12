package com.temporage.book.springboot.repository;

import com.temporage.book.springboot.domain.Category;
import com.temporage.book.springboot.domain.Post;
import com.temporage.book.springboot.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserIdx(UserInfo userIdx);
    List<Post> findByCategoryIdxAndPostIdx(Category categoryIdx, int postIdx);
    List<Post> findByCategoryIdx(Category categoryIdx);
    Post findByPostIdx(int postIdx);

    @Transactional
    void deleteByPostIdx(int postIdx);

}

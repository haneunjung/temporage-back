package com.temporage.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByEmail(String email);
    List<Board> findAll();
    List<Board> findByEmailAndCategoryId(String email, String categoryId);
    Board findById(int id);
    void deleteById(String id);
}

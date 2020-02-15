package com.temporage.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardSharedRepository extends JpaRepository<BoardShared, Long> {
    List<BoardShared> findByUserId(int userId);
    void deleteById(int id);
}

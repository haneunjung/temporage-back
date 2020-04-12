package com.temporage.book.springboot.repository;

import com.temporage.book.springboot.domain.Category;
import com.temporage.book.springboot.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUserIdx(UserInfo userIdx);
    Category findByCategoryIdx(int categoryIdx);
    Category findByCategoryNameAndUserIdx(String categoryName, UserInfo userIdx);

    @Transactional
    void deleteByCategoryIdx(int categoryIdx);
}

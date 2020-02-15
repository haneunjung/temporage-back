package com.temporage.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryName(String categoryName);
    int deleteByCategoryName(String categoryName);
}

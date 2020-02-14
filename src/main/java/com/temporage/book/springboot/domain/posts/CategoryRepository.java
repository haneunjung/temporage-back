package com.temporage.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryName(String categoryName);
    int deleteByCategoryName(String categoryName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE category SET category_name = :newCategoryName WHERE category_name = :oldCategoryName ", nativeQuery = true)
    int updateByCategory_name(String newCategoryName, String oldCategoryName);

}

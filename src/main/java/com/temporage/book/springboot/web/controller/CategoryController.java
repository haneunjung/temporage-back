package com.temporage.book.springboot.web.controller;

import com.temporage.book.springboot.domain.posts.Category;
import com.temporage.book.springboot.domain.posts.CategoryRepository;
import com.temporage.book.springboot.web.controller.dto.CategoryUpdateResponseDto;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SuppressWarnings("unchecked")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/category")
    public ResponseEntity<HttpStatus> createNewCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/category")
    public ResponseEntity<HttpStatus> updateCategoryName(@RequestBody CategoryUpdateResponseDto categoryUpdateResponseDto) {

        Category oldCategory = categoryRepository.findByCategoryName(categoryUpdateResponseDto.getOldCategoryName());
        oldCategory.setCategoryName(categoryUpdateResponseDto.getNewCategoryName());
        categoryRepository.save(oldCategory);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/category")
    public ResponseEntity<List<Category>> getCategoryList() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/category")
    public ResponseEntity<HttpStatus> deleteCategory(@RequestParam("category_name") String categoryName) {

        categoryRepository.deleteByCategoryName(categoryName);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

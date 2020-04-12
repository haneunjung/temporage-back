package com.temporage.book.springboot.controller;

import com.temporage.book.springboot.domain.Category;
import com.temporage.book.springboot.domain.UserInfo;
import com.temporage.book.springboot.repository.CategoryRepository;
import com.temporage.book.springboot.dto.CategoryInsertDto;
import com.temporage.book.springboot.dto.CategoryUpdateResponseDto;
import com.temporage.book.springboot.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SuppressWarnings("unchecked")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @PostMapping("/categories")
    public ResponseEntity<HttpStatus> createNewCategory(@RequestBody CategoryInsertDto categoryInsertDto) {
        UserInfo userInfoByUserIdx = userInfoRepository.findByUserIdx(categoryInsertDto.getUserIdx());
        categoryRepository.save(new Category(categoryInsertDto.getCategoryName(), userInfoByUserIdx));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/categories")
    public ResponseEntity<HttpStatus> updateCategoryName(@RequestBody CategoryUpdateResponseDto categoryUpdateResponseDto) {

        Category oldCategory = categoryRepository.findByCategoryIdx(categoryUpdateResponseDto.getCategoryIdx());
        Category newCategory = new Category(categoryUpdateResponseDto.getNewCategoryName(), oldCategory.getUserIdx());

        categoryRepository.save(newCategory);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/categories/user/{userIdx}")
    public ResponseEntity<List<Category>> getCategoryListByUserIdx(@PathVariable("userIdx") int userIdx) {
        UserInfo userInfoByUserIdx = userInfoRepository.findByUserIdx(userIdx);
        return new ResponseEntity<>(categoryRepository.findByUserIdx(userInfoByUserIdx), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategoryList() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/categories/{categoryIdx}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("categoryIdx") int categoryIdx) {

        categoryRepository.deleteByCategoryIdx(categoryIdx);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

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
    public ResponseEntity<Category> getCategoryList() {
        return new ResponseEntity<Category>((Category) categoryRepository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/category")
    public JSONObject deleteCategory(@RequestParam("category_name") String categoryName) {
        JSONObject result = new JSONObject();

        try {
            int returnValue = categoryRepository.deleteByCategory_name(categoryName);
            result.put("result", 1);
            result.put("message", "success value : " + result);
        } catch (Exception e) {
            result.put("result", 0);
            result.put("message", e.toString());
        }

        return result;
    }
}

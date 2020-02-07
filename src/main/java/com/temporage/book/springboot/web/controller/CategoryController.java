package com.temporage.book.springboot.web.controller;

import com.temporage.book.springboot.domain.posts.Category;
import com.temporage.book.springboot.domain.posts.CategoryRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SuppressWarnings("unchecked")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/category/create-new-category")
    public JSONObject createNewCategory(@RequestParam("category_name") String categorName){

        JSONObject result = new JSONObject();

        try{
            Category category = new Category(categorName);
            categoryRepository.save(category);

            result.put("result", 1);
            result.put("message", "success");
        }catch (Exception e){
            result.put("result", 0);
            result.put("message", e.toString());
        }

        return result;
    }

    @PutMapping("/category/update-category-name")
    public JSONObject updateCategoryName(@RequestParam("oldCategoryName") String oldCategoryName, @RequestParam("newCategoryName") String newCategoryName){
        JSONObject result = new JSONObject();

        try{
            int return_valye = categoryRepository.updateByCategory_name(newCategoryName, oldCategoryName);
            result.put("result", 1);
            result.put("message", "success");
        }catch (Exception e){
            result.put("result", 0);
            result.put("message", e.toString());
        }

        return result;
    }


    @GetMapping("/category/get-category-list")
    public JSONObject getCategoryList(){
        JSONObject result = new JSONObject();

        try{
            List<Category> category_list = categoryRepository.findAllCategory();

            result.put("result", 1);
            result.put("message", category_list);

        }catch (Exception e){
            result.put("result", 0);
            result.put("message", e.toString());
        }

        return result;

    }

    @DeleteMapping("/category/delete-category")
    public JSONObject deleteCategory(@RequestParam("category_name") String category_name){
        JSONObject result = new JSONObject();

        try{
            int return_message = categoryRepository.deleteByCategory_name(category_name);
            result.put("result", 1);
            result.put("message", "success");
        }catch (Exception e){
            result.put("result", 0);
            result.put("message", e.toString());
        }

        return result;

    }
}

package com.temporage.book.springboot.controller;

import com.temporage.book.springboot.domain.UserInfo;
import com.temporage.book.springboot.dto.PostResponseDto;
import com.temporage.book.springboot.domain.Category;
import com.temporage.book.springboot.repository.CategoryRepository;
import com.temporage.book.springboot.domain.Post;
import com.temporage.book.springboot.repository.PostRepository;
import com.temporage.book.springboot.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@SuppressWarnings("unchecked")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    private final String LOGIN = "login";

    @PutMapping("/categories/posts")
    public ResponseEntity<HttpStatus> updatePost(@RequestBody PostResponseDto postResponseDto) {
        Post oldPost = postRepository.findByPostIdx(postResponseDto.getPostIdx());
        Category categoryByCategoryIdx = categoryRepository.findByCategoryIdx(postResponseDto.getNewCategoryIdx());
        Post newPost = new Post(postResponseDto.getNewPostContents(),categoryByCategoryIdx, oldPost.getUserIdx());
        postRepository.save(newPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/categories/{categoryName}/posts")
    public ResponseEntity<HttpStatus> createPost(@RequestBody PostResponseDto postResponseDto, @PathVariable("categoryName") String categoryName, HttpServletRequest request) {

        int userIdx = (int)request.getSession().getAttribute(LOGIN);
        UserInfo userInfoByUserIdx = userInfoRepository.findByUserIdx(userIdx);

        Category categoryByCategoryNameAndUserIdx = categoryRepository.findByCategoryNameAndUserIdx(categoryName, userInfoByUserIdx);

        Post savePost = new Post(postResponseDto.getNewPostContents(), categoryByCategoryNameAndUserIdx, userInfoByUserIdx);
        postRepository.save(savePost);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/categories/user/{userIdx}/posts")
    public ResponseEntity<List<Post>> getPostsByUserIdx(@PathVariable("userIdx") int userIdx) {
        UserInfo userInfoByUserIdx = userInfoRepository.findByUserIdx(userIdx);
        return new ResponseEntity<>(postRepository.findByUserIdx(userInfoByUserIdx), HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryIdx}/posts")
    public ResponseEntity<List<Post>> getPostsByCategoryIdx(@PathVariable("categoryIdx") int categoryIdx) {
        Category categoryByCategoryIdx = categoryRepository.findByCategoryIdx(categoryIdx);
        return new ResponseEntity<>(postRepository.findByCategoryIdx(categoryByCategoryIdx), HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryIdx}/posts/{postIdx}")
    public ResponseEntity<List<Post>> getPostsByCategoryIdxAndPostIdx(@PathVariable("categoryIdx") int categoryIdx, @PathVariable("postIdx") int postIdx) {
        Category categoryByCategoryIdx = categoryRepository.findByCategoryIdx(categoryIdx);
        return new ResponseEntity<>(postRepository.findByCategoryIdxAndPostIdx(categoryByCategoryIdx, postIdx), HttpStatus.OK);
    }

    @DeleteMapping("/categories/posts/{postIdx}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("postIdx") int postIdx) {
        postRepository.deleteByPostIdx(postIdx);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

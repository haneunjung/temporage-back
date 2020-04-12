package com.temporage.book.springboot.service;

import com.temporage.book.springboot.repository.CategoryRepository;
import com.temporage.book.springboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostRepository postRepository;



}
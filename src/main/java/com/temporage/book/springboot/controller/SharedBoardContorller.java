package com.temporage.book.springboot.controller;

import com.temporage.book.springboot.domain.Post;
import com.temporage.book.springboot.dto.PostSharedResponseDto;
import com.temporage.book.springboot.domain.PostShared;
import com.temporage.book.springboot.repository.CategoryRepository;
import com.temporage.book.springboot.repository.PostRepository;
import com.temporage.book.springboot.repository.PostSharedRepository;
import com.temporage.book.springboot.domain.UserInfo;
import com.temporage.book.springboot.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SharedBoardContorller {

    @Autowired
    PostSharedRepository postSharedRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @PostMapping("/categories/post/shared")
    public ResponseEntity<HttpStatus> sharingBoard(@RequestBody PostSharedResponseDto postSharedResponseDto){

        Post postIdx = postRepository.findByPostIdx(postSharedResponseDto.getPostIdx());
        UserInfo userIdx = userInfoRepository.findByUserIdx(postSharedResponseDto.getUserIdx());

        PostShared boardShared = new PostShared(postIdx, userIdx);
        postSharedRepository.save(boardShared);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/categories/user/{userIdx}/post/shared")
    public ResponseEntity<List<PostShared>> getSharedBoards(@PathVariable("userIdx") int userIdx){
        UserInfo userInfoByUserIdx = userInfoRepository.findByUserIdx(userIdx);
        return new ResponseEntity<>(postSharedRepository.findByUserIdx(userInfoByUserIdx), HttpStatus.OK);
    }

    @PutMapping("/categories/post/shared/{sharedPostIdx}")
    public ResponseEntity<HttpStatus> updateSharedBoards(@PathVariable("sharedPostIdx") int sharedPostIdx){
        PostShared oldPostShared =  postSharedRepository.findBySharedPostIdx(sharedPostIdx);
        PostShared newPostShared = new PostShared(oldPostShared.getPostIdx(), oldPostShared.getUserIdx());
        postSharedRepository.save(newPostShared);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/categories/post/shared")
    public ResponseEntity<HttpStatus> deleteSharedBoards(@RequestBody PostSharedResponseDto postSharedResponseDto){
        postSharedRepository.deleteBySharedPostIdx(postSharedResponseDto.getSharedPostIdx());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

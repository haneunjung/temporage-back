package com.temporage.book.springboot.web.controller;

import com.temporage.book.springboot.domain.posts.BoardShared;
import com.temporage.book.springboot.domain.posts.BoardSharedRepository;
import com.temporage.book.springboot.web.controller.dto.BoardResponseDto;
import com.temporage.book.springboot.web.controller.dto.BoardSharedResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SharedBoardContorller {

    @Autowired
    BoardSharedRepository boardSharedRepository;

    // TODO:공유 board 가 저장될때, UserId 가 어떤식으로 저장될것인가? row별로 각각 하나씩 저장되는게 낫다고 저번주에 말한거같은데. 확인차.
    @PostMapping("/boards/share")
    public ResponseEntity<HttpStatus> sharingBoard(@RequestBody BoardSharedResponseDto boardSharedResponseDto){
        BoardShared boardShared = new BoardShared(boardSharedResponseDto.getBoardId(), boardSharedResponseDto.getUserId());
        boardSharedRepository.save(boardShared);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/boards/share")
    public ResponseEntity<List<BoardShared>> getSharedBoards(@RequestBody BoardSharedResponseDto boardSharedResponseDto){
        return new ResponseEntity<>(boardSharedRepository.findByUserId(boardSharedResponseDto.getUserId()), HttpStatus.OK);
    }

    @PutMapping("/boards/share")
    public ResponseEntity<HttpStatus> updateSharedBoards(@RequestBody BoardSharedResponseDto boardSharedResponseDto){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/boards/share")
    public ResponseEntity<HttpStatus> deleteSharedBoards(@RequestBody BoardSharedResponseDto boardSharedResponseDto){
        boardSharedRepository.deleteById(boardSharedResponseDto.getSharedBoardId());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

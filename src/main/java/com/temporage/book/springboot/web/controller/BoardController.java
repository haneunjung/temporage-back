package com.temporage.book.springboot.web.controller;

import com.temporage.book.springboot.domain.posts.Board;
import com.temporage.book.springboot.domain.posts.BoardRepository;
import com.temporage.book.springboot.web.controller.dto.BoardResponseDto;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@SuppressWarnings("unchecked")
public class BoardController {
    @Autowired
    BoardRepository boardRepository;

    @PutMapping("/board")
    public ResponseEntity<HttpStatus> updatePost(@RequestBody Board new_board) {
        Board old_board = boardRepository.findById(new_board.getId());
        old_board.setCategoryId(new_board.getCategoryId());
        old_board.setContents(new_board.getContents());
        old_board.setEmail(new_board.getEmail());
        boardRepository.save(old_board);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/boards")
    public ResponseEntity<HttpStatus> createPost(@RequestBody BoardResponseDto boardResponseDto) {

        Board save_board = new Board(boardResponseDto.getContents(), boardResponseDto.getEmail(), boardResponseDto.getCategoryId());
        boardRepository.save(save_board);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/boards")
    public ResponseEntity<List<Board>> getAllPosts() {
        return new ResponseEntity<>(boardRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/boards/user/{email}")
    public ResponseEntity<List<Board>> getUserPosts(@PathVariable("email") String email) {
        return new ResponseEntity<>(boardRepository.findByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/boards/user/{email}/category")
    public ResponseEntity<List<Board>> getUserCategoryPosts(@PathVariable("email") String email, @RequestParam("categoryId") String categoryId) {
        return new ResponseEntity<>(boardRepository.findByEmailAndCategoryId(email,categoryId), HttpStatus.OK);
    }

    //TODO: 성공 여부는 보통 http status code 로 사용
    @DeleteMapping("/boards/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable String id) {
        boardRepository.deleteByBoardId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

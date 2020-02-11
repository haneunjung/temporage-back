package com.temporage.book.springboot.web.controller;

import com.temporage.book.springboot.domain.posts.Board;
import com.temporage.book.springboot.domain.posts.BoardRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@SuppressWarnings("unchecked")
public class BoardController {
    @Autowired
    BoardRepository boardRepository;

//    @PutMapping("/board/update-post")
//    public JSONObject updatePost(@RequestParam Map<String, String> param) {
//        JSONObject result = new JSONObject();
//
//        try {
//            boardRepository.updatePost(param.get("newTitle"), param.get("newContents"), param.get("boardId"));
//
//            result.put("result", 1);
//            result.put("message", "success");
//        } catch (Exception e) {
//            result.put("result", 0);
//            result.put("message", e.toString());
//        }
//
//        return result;
//    }

    @PostMapping("/boards")
    public JSONObject createPost(@ModelAttribute Board board) {
        JSONObject result = new JSONObject();

        try {
            Board save_board = new Board(board.getContents(), board.getEmail(), board.getCategoryId());
            boardRepository.save(save_board);

            result.put("result", 1);
            result.put("message", "success");
        } catch (Exception e) {
            result.put("result", 0);
            result.put("message", e.toString());
        }
        return result;
    }


    @GetMapping("/category/get-all-post")
    public JSONObject getAllPosts() {
        JSONObject result = new JSONObject();

        try {
            List<Board> board_list = boardRepository.findAll();

            result.put("result", 1);
            result.put("message", board_list);

        } catch (Exception e) {
            result.put("result", 0);
            result.put("message", e.toString());
        }
        return result;
    }

    @GetMapping("/boards")
    public JSONObject getPostsByEmail(@RequestParam("email") String email) {
        JSONObject result = new JSONObject();

        try {
            List<Board> board_list = boardRepository.findByEmail(email);

            result.put("result", 1);
            result.put("message", board_list);

        } catch (Exception e) {
            result.put("result", 0);
            result.put("message", e.toString());
        }

        return result;

    }

    //TODO: 성공 여부는 보통 http status code 로 사용
    @DeleteMapping("/boards/{id}")
    public JSONObject deleteCategory(@PathVariable String id) {
        JSONObject result = new JSONObject();

        try {
            boardRepository.deleteByBoardId(id);

            result.put("result", 1);
            result.put("message", "success");
        } catch (Exception e) {
            result.put("result", 0);
            result.put("message", e.toString());
        }

        return result;
    }
}

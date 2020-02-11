package com.temporage.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE board SET (title=:newTitle, contents=:newContents) WHERE id = boardId", nativeQuery = true)
//    void updatePost(String newTitle, String newContents, String boardId);

    List<Board> findByEmail(String email);

    List<Board> findAll();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM board WHERE boardId = :boardId", nativeQuery = true)
    void deleteByBoardId(String boardId);


}

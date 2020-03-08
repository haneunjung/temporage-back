package com.temporage.book.springboot.repository;

import com.temporage.book.springboot.domain.PostShared;
import com.temporage.book.springboot.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostSharedRepository extends JpaRepository<PostShared, Long> {
    List<PostShared> findByUserIdx(UserInfo userInfo);
    PostShared findBySharedPostIdx(int sharedPostIdx);

    @Transactional
    void deleteBySharedPostIdx(int sharedPostIdx);
}

package com.temporage.book.springboot.repository;

import com.temporage.book.springboot.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserEmail(String userEmail);
    UserInfo findByUserIdx(int userIdx);
}

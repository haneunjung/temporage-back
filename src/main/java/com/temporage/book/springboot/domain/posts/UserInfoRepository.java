package com.temporage.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByEmail(String email);
}

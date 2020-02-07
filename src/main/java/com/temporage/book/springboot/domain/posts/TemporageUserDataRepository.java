package com.temporage.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemporageUserDataRepository extends JpaRepository<TemporageUserData, Long> {
    TemporageUserData findByEmail(String email);
}

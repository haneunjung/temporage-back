package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface TemporageSessionRepository extends JpaRepository<TemporageSession, Long> {
    TemporageSession findByEmail(String email);

    @Query(value = "INSERT INTO temporage_session(email, session_id, create_date) " +
            "VALUES(:email, :sessionId, :create_date)", nativeQuery = true)
    void setSessionInfo(String email, String sessionId, LocalDate create_date);
}

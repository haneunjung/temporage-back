package com.temporage.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "temporage_session")
public class TemporageSession {
    @Id
    @Column(length = 64, nullable = false)
    private String email;

    @Column(length = 128, nullable = false)
    private String sessionId;

    @Column
    @CreationTimestamp
    private LocalDate create_date;

    @Builder
    public TemporageSession(String sessionId, String email){
        this.sessionId = sessionId;
        this.email = email;
    }
}

package com.temporage.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 128, nullable = false)
    private String email;

    @Column(length = 128, nullable = false)
    private String password;

    @Column(length = 32, nullable = false)
    private String name;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    @CreationTimestamp
    private LocalDateTime create_time;


    @Builder
    public UserInfo(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }
}

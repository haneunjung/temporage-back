package com.temporage.book.springboot.web.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class BoardResponseDto {

    private final String categoryId; // 카테고리 ID
    private final String contents; // 내용
    private final String email; //작성자

}

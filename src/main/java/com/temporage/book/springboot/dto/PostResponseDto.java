package com.temporage.book.springboot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostResponseDto {
    private int postIdx;
    private int newCategoryIdx;
    private String newPostContents; // 내용
}

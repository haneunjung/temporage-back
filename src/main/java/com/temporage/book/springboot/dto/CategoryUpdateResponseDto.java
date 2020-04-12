package com.temporage.book.springboot.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CategoryUpdateResponseDto {
    private int categoryIdx;
    private String newCategoryName;
}



package com.temporage.book.springboot.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CategoryInsertDto {
    private int userIdx;
    private String categoryName;
}

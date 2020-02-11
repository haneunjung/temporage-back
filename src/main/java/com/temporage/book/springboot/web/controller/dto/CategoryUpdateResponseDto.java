package com.temporage.book.springboot.web.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CategoryUpdateResponseDto {
    private final String oldCategoryName;
    private final String newCategoryName;

}

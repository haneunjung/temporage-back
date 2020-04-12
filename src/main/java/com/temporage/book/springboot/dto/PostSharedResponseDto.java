package com.temporage.book.springboot.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostSharedResponseDto {
    private int sharedPostIdx;
    private int postIdx;
    private int userIdx;
}

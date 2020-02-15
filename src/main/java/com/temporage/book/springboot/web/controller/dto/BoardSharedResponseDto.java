package com.temporage.book.springboot.web.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardSharedResponseDto {
    private final int boardId;
    private final int userId;
    private final int sharedBoardId;
}

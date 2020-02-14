package com.temporage.book.springboot.web.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserInfoDto {
    public final String email;
    public final String password;
    public final String name;
    public final String sessionId = "default";
}

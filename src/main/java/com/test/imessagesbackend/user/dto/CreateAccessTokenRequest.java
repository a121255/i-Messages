package com.test.imessagesbackend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateAccessTokenRequest {
    private String refreshToken;
}

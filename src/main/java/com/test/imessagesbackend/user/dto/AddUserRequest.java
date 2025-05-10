package com.test.imessagesbackend.user.dto;

import lombok.Getter;

@Getter
public class AddUserRequest {
    private String email;
    private String password;
}

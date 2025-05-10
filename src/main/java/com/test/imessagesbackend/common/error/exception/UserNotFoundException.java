package com.test.imessagesbackend.common.error.exception;

import com.test.imessagesbackend.common.error.code.ErrorCode;

public class UserNotFoundException extends BaseException{
    public UserNotFoundException(Long userId) {
        super(ErrorCode.USER_NOT_FOUND, "User found with id: " + userId);
    }

    public UserNotFoundException(String email) {
        super(ErrorCode.USER_NOT_FOUND, "User found with email: " + email);
    }

    public UserNotFoundException(Long userId, String message) {
        super(ErrorCode.USER_NOT_FOUND, message + ": " + userId);
    }
}

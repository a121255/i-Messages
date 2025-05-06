package com.test.imessagesbackend.common.error.exception;

import com.test.imessagesbackend.common.error.code.ErrorCode;

public class UserNotFountException extends BaseException{
    public UserNotFountException(Long userId) {
        super(ErrorCode.USER_NOT_FOUND, "User found with id: " + userId);
    }

    public UserNotFountException(Long userId, String message) {
        super(ErrorCode.USER_NOT_FOUND, message + ": " + userId);
    }
}

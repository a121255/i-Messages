package com.test.imessagesbackend.common.error.exception;

import com.test.imessagesbackend.common.error.code.ErrorCode;

public class JwtAuthenticationException extends BaseException {

    public JwtAuthenticationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public JwtAuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}

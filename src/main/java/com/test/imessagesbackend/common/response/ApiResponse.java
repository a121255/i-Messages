package com.test.imessagesbackend.common.response;

import com.test.imessagesbackend.common.error.code.ErrorCode;
import lombok.Builder;
import lombok.Getter;

/*
* API 응답 표준화 클래스
* */

@Builder
@Getter
public class ApiResponse<T> {
    private Integer code;
    private String message;
    private T data;


    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .code(ErrorCode.OK.getErrorCode())
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .code(ErrorCode.OK.getErrorCode())
                .message(ErrorCode.OK.getDescription())
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode, String description) {
        return ApiResponse.<T>builder()
                .code(errorCode.getErrorCode())
                .message(description)
                .build();
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return ApiResponse.<T>builder()
                .code(errorCode.getErrorCode())
                .message(errorCode.getDescription())
                .build();
    }

    public static <T> ApiResponse<T> error(String description) {
        return ApiResponse.<T>builder()
                .code(ErrorCode.INTERNAL_SERVER_ERROR.getErrorCode())
                .message(description)
                .build();
    }




}

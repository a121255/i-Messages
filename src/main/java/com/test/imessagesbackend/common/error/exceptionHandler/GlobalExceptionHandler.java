package com.test.imessagesbackend.common.error.exceptionHandler;

import com.test.imessagesbackend.common.error.code.ErrorCode;
import com.test.imessagesbackend.common.error.exception.BaseException;
import com.test.imessagesbackend.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<Void>> handleBaseException(BaseException ex) {
        HttpStatus status = resolveStatusFromErrorCode(ex.getErrorCode());
        return ResponseEntity
                .status(status)
                .body(ApiResponse.error(ex.getErrorCode(), ex.getMessage()));
    }

    // HTTP 상태 코드 결정 헬퍼 메소드
    private HttpStatus resolveStatusFromErrorCode(ErrorCode errorCode) {
        // ErrorCode에 따라 적절한 HTTP 상태 코드 반환
        switch (errorCode) {
            case NOT_FOUND:
            case USER_NOT_FOUND:
            case ROOM_NOT_FOUND:
                return HttpStatus.NOT_FOUND;
            case BAD_REQUEST:
            case INVALID_FORMAT:
                return HttpStatus.BAD_REQUEST;
            // 기타 다른 케이스들...
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    // 표준 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGlobalException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(ErrorCode.INTERNAL_SERVER_ERROR,
                        "서버 내부 오류가 발생했습니다."));
    }
}

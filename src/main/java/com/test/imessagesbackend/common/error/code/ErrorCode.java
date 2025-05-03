package com.test.imessagesbackend.common.error.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // 성공 (2xx)
    OK(HttpStatus.OK, 2000, "요청이 성공적으로 처리되었습니다."),
    CREATED(HttpStatus.CREATED, 2001, "리소스가 성공적으로 생성되었습니다."),

    // 클라이언트 오류 (4xx)
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 4000, "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 4001, "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, 4003, "접근 권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, 4004, "요청한 리소스를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, 4005, "지원하지 않는 HTTP 메소드입니다."),
    CONFLICT(HttpStatus.CONFLICT, 4009, "리소스 충돌이 발생했습니다."),

    // 시스템 오류 (5xx)
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5000, "서버 내부 오류가 발생했습니다."),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, 5003, "서비스를 일시적으로 사용할 수 없습니다."),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5001, "데이터베이스 오류가 발생했습니다."),
    EXTERNAL_API_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5002, "외부 API 호출 중 오류가 발생했습니다."),

    // 인증/인가 관련 (41xx)
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, 4100, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, 4101, "만료된 토큰입니다."),
    INSUFFICIENT_PERMISSIONS(HttpStatus.FORBIDDEN, 4102, "필요한 권한이 부족합니다."),

    // Room 관련 (42xx)
    ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, 4200, "요청한 방을 찾을 수 없습니다."),
    ROOM_FULL(HttpStatus.BAD_REQUEST, 4201, "방이 가득 찼습니다."),
    ROOM_CLOSED(HttpStatus.BAD_REQUEST, 4202, "이미 닫힌 방입니다."),
    INVALID_ROOM_PASSWORD(HttpStatus.UNAUTHORIZED, 4203, "방 비밀번호가 올바르지 않습니다."),

    // User 관련 (43xx)
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 4300, "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, 4301, "이미 존재하는 사용자입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, 4302, "잘못된 비밀번호입니다."),
    INACTIVE_USER(HttpStatus.FORBIDDEN, 4303, "비활성화된 사용자입니다."),

    // Message 관련 (44xx)
    MESSAGE_NOT_FOUND(HttpStatus.NOT_FOUND, 4400, "메시지를 찾을 수 없습니다."),
    MESSAGE_DELETED(HttpStatus.BAD_REQUEST, 4401, "삭제된 메시지입니다."),

    // 입력값 검증 관련 (45xx)
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, 4500, "유효하지 않은 입력값입니다."),
    MISSING_REQUIRED_FIELD(HttpStatus.BAD_REQUEST, 4501, "필수 필드가 누락되었습니다."),
    INVALID_FORMAT(HttpStatus.BAD_REQUEST, 4502, "잘못된 형식입니다."),
    INVALID_LENGTH(HttpStatus.BAD_REQUEST, 4503, "길이가 유효하지 않습니다.");


    private final HttpStatus httpStatus;
    private final int errorCode;
    private final String description;



}

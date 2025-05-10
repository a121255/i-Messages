package com.test.imessagesbackend.user.controller;

import com.test.imessagesbackend.common.response.ApiResponse;
import com.test.imessagesbackend.user.dto.*;
import com.test.imessagesbackend.user.service.AuthService;
import com.test.imessagesbackend.user.service.TokenService;
import com.test.imessagesbackend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@Tag(name = "인증/인가", description = "인증/인가 관련 API를 제공합니다.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final TokenService tokenService;
    private final UserService userService;
    private final AuthService authService;

    @Operation(summary = "토큰 발급", description = "JWT 토큰을 발급합니다.")
    @PostMapping("/token")
    public ApiResponse<CreateAccessTokenResponse> createNewAccessToken(@RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAceessToken(request.getRefreshToken());

        return ApiResponse.success(new CreateAccessTokenResponse(newAccessToken));
    }

    @Operation(summary = "회원가입", description = "ID/PW을 입력 받아 회원가입을 처리합니다.")
    @PostMapping("/signup")
    public ApiResponse<String> signup(@RequestBody AddUserRequest request) {
        userService.save(request);
        return ApiResponse.success("회원가입 성공");
    }

    @Operation(summary = "로그인", description = "ID/PW를 입력 받아 로그인합니다.")
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ApiResponse.success(response);
    }

    @Operation(summary = "로그아웃", description = "로그아웃합니다.")
    @PostMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());

        return ApiResponse.success("로그아웃 성공");
    }
}

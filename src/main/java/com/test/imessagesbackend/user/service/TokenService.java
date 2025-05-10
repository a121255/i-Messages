package com.test.imessagesbackend.user.service;

import com.test.imessagesbackend.common.config.jwt.TokenProvider;
import com.test.imessagesbackend.user.entity.RefreshToken;
import com.test.imessagesbackend.user.entity.User;
import com.test.imessagesbackend.user.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;


    public String createNewAceessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = this.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofDays(2));
    }

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}

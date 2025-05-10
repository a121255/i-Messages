package com.test.imessagesbackend.user.service;

import com.test.imessagesbackend.common.config.jwt.TokenProvider;
import com.test.imessagesbackend.common.error.code.ErrorCode;
import com.test.imessagesbackend.common.error.exception.JwtAuthenticationException;
import com.test.imessagesbackend.user.dto.LoginRequest;
import com.test.imessagesbackend.user.dto.LoginResponse;
import com.test.imessagesbackend.user.entity.RefreshToken;

import com.test.imessagesbackend.user.entity.User;
import com.test.imessagesbackend.user.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticate(request);
        User user = (User) authentication.getPrincipal();

        // 토큰 생성
        String accessToken = tokenProvider.generateToken(user, Duration.ofDays(1));
        String refreshToken = tokenProvider.generateToken(user, Duration.ofDays(7));

        // 리프레시 토큰 저장
        refreshTokenRepository.save(new RefreshToken(user.getId(), refreshToken));

        return new LoginResponse(accessToken, refreshToken);
    }

    private Authentication authenticate(LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            return authenticationManager.authenticate(authToken);
        } catch (UsernameNotFoundException ex) {
            throw new JwtAuthenticationException(ErrorCode.USER_NOT_FOUND);
        } catch (BadCredentialsException ex) {
            throw new JwtAuthenticationException(ErrorCode.INVALID_PASSWORD);
        } catch (DisabledException ex) {
            throw new JwtAuthenticationException(ErrorCode.INACTIVE_USER);
        } catch (AuthenticationException ex) {
            throw new JwtAuthenticationException(ErrorCode.INVALID_TOKEN);
        }
    }
}

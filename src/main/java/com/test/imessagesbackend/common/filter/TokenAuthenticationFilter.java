package com.test.imessagesbackend.common.filter;

import com.test.imessagesbackend.common.config.jwt.TokenProvider;
import com.test.imessagesbackend.common.error.code.ErrorCode;
import com.test.imessagesbackend.common.error.exception.JwtAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 요청 헤더의 Authorization 키의 값 조회
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);

        // 가져온 값에서 접두사 제거
        String token = getAccessToken(authorizationHeader);

        try {
            if (token == null) {
                log.debug("No JWT token found in request.");
            } else {
                if (!tokenProvider.validToken(token)) {
                    throw new JwtAuthenticationException(ErrorCode.INVALID_TOKEN);
                }

                Authentication authentication = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.debug("Authentication success for user: {}", authentication.getName());
            }

            filterChain.doFilter(request, response);

        } catch (JwtAuthenticationException e) {
            log.warn("JWT authentication failed: {}", e.getMessage());
            sendUnauthorizedResponse(response, e.getMessage(), e.getErrorCode());
        }
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, String message, ErrorCode code) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String responseBody = String.format("{\"status\":401,\"message\":\"%s\",\"code\":\"%s\"}", message, code);
        response.getWriter().write(responseBody);
    }


    private String getAccessToken(String authorizationHeader) {
        if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

}

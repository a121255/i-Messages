package com.test.imessagesbackend.common.util;

import com.test.imessagesbackend.common.error.code.ErrorCode;
import com.test.imessagesbackend.common.error.exception.JwtAuthenticationException;
import com.test.imessagesbackend.common.error.exception.UserNotFoundException;
import com.test.imessagesbackend.user.entity.User;
import com.test.imessagesbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AuthenticationUtil {

    private final UserRepository userRepository;

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new JwtAuthenticationException(ErrorCode.UNAUTHORIZED);
        }

        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserDetails)) {
            throw new JwtAuthenticationException(ErrorCode.INVALID_TOKEN);
        }

        UserDetails userDetails = (UserDetails) principal;  // UserDetails로 캐스팅
        String email = userDetails.getUsername();  // UserDetails에서 이메일을 가져옴

        // 이메일을 기반으로 DB에서 사용자 정보 조회
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public Long getUserId() {
        return getAuthenticatedUser().getId();
    }

    public String getUserEmail() {
        return getAuthenticatedUser().getEmail();
    }

    public User getUserInfo() {
        return getAuthenticatedUser();
    }
}

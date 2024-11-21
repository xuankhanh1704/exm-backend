package org.se06203.campusexpensemanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.se06203.campusexpensemanagement.config.ErrorCode;
import org.se06203.campusexpensemanagement.config.exception.IncorrectPasswordException;
import org.se06203.campusexpensemanagement.config.exception.NotFoundException;
import org.se06203.campusexpensemanagement.config.exception.ServerException;
import org.se06203.campusexpensemanagement.config.exception.SocialUserNotFoundException;
import org.se06203.campusexpensemanagement.config.security.JwtService;
import org.se06203.campusexpensemanagement.config.security.SecurityUtils;
import org.se06203.campusexpensemanagement.config.security.SpringSecurityUser;
import org.se06203.campusexpensemanagement.dto.common.TokenPayload;
import org.se06203.campusexpensemanagement.dto.response.AuthenticationResponse;
import org.se06203.campusexpensemanagement.persistence.entity.Accounts;
import org.se06203.campusexpensemanagement.persistence.repository.AccountRepository;
import org.se06203.campusexpensemanagement.persistence.repository.UserRepository;
import org.se06203.campusexpensemanagement.utils.Constants;
import org.se06203.campusexpensemanagement.utils.MapperUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BaseHandler {

    protected final JwtService jwtService;
    protected final UserRepository userRepository;
    protected final OTPService otpService;
    protected final PasswordEncoder passwordEncoder;
    protected final AccountRepository accountRepository;

    protected AuthenticationResponse setAuthenticationContextAndGenerateToken(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var token = jwtService.createToken(authentication);
        return AuthenticationResponse.builder()
                .token(token.token())
                .refreshToken(jwtService.createRefreshToken(authentication, SecurityUtils.getAuthenticatedUser().getRole()))
                .build();
    }

    public SpringSecurityUser getAuthenticatedUser(String email, String password) {
        return getAuthenticatedUser(email, null, password);
    }

    public SpringSecurityUser getAuthenticatedUser(String email, TokenPayload payload, String password) {
        if (StringUtils.isNotBlank(password)) {
            var user = userRepository
                    .findByEmailAndRole(email, Constants.role.USER)
                    .orElseThrow(() -> new NotFoundException("user", email));

            var userAuthorities = accountRepository.findAllByUserId(user.getId());

            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new NotFoundException();
            }
            return SpringSecurityUser.fromUser(user, userAuthorities.stream()
                    .map(authority -> authority.getRole().name())
                    .toList(), Constants.role.USER);
        }


        return userRepository
                .findByEmailAndRole(email, Constants.role.USER)
                .map(user -> {
                    var userAuthorities = accountRepository.findAllByUserId(user.getId());
                    return SpringSecurityUser.fromUser(user, userAuthorities.stream()
                            .map(authority -> authority.getRole().name())
                            .toList(), Constants.role.USER);
                })
                .orElseThrow(() -> new SocialUserNotFoundException(MapperUtils.toJsonString(payload)));
    }
}

package org.se06203.campusexpensemanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.se06203.campusexpensemanagement.config.ErrorCode;
import org.se06203.campusexpensemanagement.config.exception.*;
import org.se06203.campusexpensemanagement.config.security.JwtService;
import org.se06203.campusexpensemanagement.config.security.SpringSecurityUser;
import org.se06203.campusexpensemanagement.dto.request.EmailRequest;
import org.se06203.campusexpensemanagement.dto.request.RegisterRequest;
import org.se06203.campusexpensemanagement.dto.response.AuthenticationResponse;
import org.se06203.campusexpensemanagement.persistence.entity.Accounts;
import org.se06203.campusexpensemanagement.persistence.entity.Users;
import org.se06203.campusexpensemanagement.persistence.repository.AccountRepository;
import org.se06203.campusexpensemanagement.persistence.repository.UserRepository;
import org.se06203.campusexpensemanagement.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService extends BaseHandler {

    private final AccountRepository accountRepository;

    @Autowired
    public AuthService(JwtService jwtService,
                       UserRepository userRepository,
                       OTPService otpService,
                       PasswordEncoder passwordEncoder,
                       AccountRepository accountRepository
    ) {
        super(jwtService, userRepository, otpService, passwordEncoder, accountRepository);
        this.accountRepository = accountRepository;
    }

    public AuthenticationResponse authenticate(EmailRequest request) {

        SpringSecurityUser springSecurityUser = getAuthenticatedUser(request.getEmail(), request.getPassword());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                springSecurityUser,
                request.getPassword(),
                springSecurityUser.getAuthorities()
        );

        try {
            //        todo BONUS_PRICE from admin
            return super.setAuthenticationContextAndGenerateToken(authenticationToken);
        } catch (BadCredentialsException ex) {
            throw new NotFoundException("Cannot found account");
        }
    }

    public AuthenticationResponse register(RegisterRequest rq) {
        if (!otpService.isValidOtpTransaction(rq.getTransId(), rq.getEmail())) {
            throw new OtpInvalidException("register");
        }

        userRepository.findByEmailAndRole(rq.getEmail(), Constants.role.USER)
                .ifPresentOrElse(
                        user -> {
                            throw new RegisterEmailExistException();
                        },
                        () -> {
                            var savedUser = userRepository.save(Users.builder()
                                    .userName(rq.getUserName())
                                    .phone(rq.getPhoneNumber())
                                    .email(rq.getEmail())
                                    .password(passwordEncoder.encode(rq.getPassword()))
                                    .build());

                            accountRepository.save(Accounts.builder()
                                    .user(savedUser)
                                    .role(Constants.role.USER)
                                    .build());
                        }
                );
        //        todo BONUS_PRICE from admin
        return this.authenticate(EmailRequest.builder()
                .email(rq.getEmail())
                .password(rq.getPassword())
                .build());
    }

//    public void changePassword(ChangePasswordRequest rq) {
//        var sUser = SecurityUtils.getAuthenticatedUser();
//        var attempt = this.getOrCreateUserAttemptedAction(sUser.getId(), Constants.ActionTypeEnum.LOGIN);
//        if (attempt.getCount() >= Constants.MAX_LOGIN_ATTEMPT) {
//            throw new IncorrectPasswordException(Constants.MAX_LOGIN_ATTEMPT);
//        }
//
//        var user = userRepository.findById(sUser.getId())
//                .map(u -> {
//                    if (!passwordEncoder.matches(rq.getCurrentPassword(), u.getPassword())) {
//                        attempt.setCount(attempt.getCount() + 1);
//                        throw new IncorrectPasswordException(attempt.getCount());
//                    }
//                    u.setPassword(passwordEncoder.encode(rq.getNewPassword()));
//                    return u;
//                })
//                .orElseThrow(() -> new NotFoundException("user", sUser.getId().toString()));
//
//        attempt.setCount(0);
//        attemptedActionRepository.save(attempt);
//        userRepository.save(user);
//    }
//
//    public void resetPassword(ResetPasswordRequest rq) {
//        //Check trans_id exist into redis
//        String resultKey = Constants.K_OTP_TRANS_RESULT.formatted(rq.getTransId());
//        var phoneNumber = redisTemplate.opsForValue().get(resultKey);
//
//        if (StringUtils.isEmpty(phoneNumber)) {
//            throw new OtpInvalidException("resetPassword");
//        }
//
//        var user = userRepository.findByLoginAndUserType(phoneNumber, userType)
//                .orElseThrow(() -> new NotFoundException("user", phoneNumber));
//        if (!user.getIsActive()) {
//            throw new ServerException(ErrorCode.INACTIVE_USER, "Login Fail. Your account is inactive. Please contact admin for assistance.");
//        }
//
//        var attempt = this.getOrCreateUserAttemptedAction(user.getId(), Constants.ActionTypeEnum.LOGIN);
//        if (attempt.getCount() > 0) {
//            attempt.setCount(0);
//            attemptedActionRepository.save(attempt);
//        }
//
//        user.setPassword(passwordEncoder.encode(rq.getResetPassword()));
//        userRepository.save(user);
//    }
//
//    public CheckAccountResponse checkAccountExist(CheckAccountRequest rq, Constants.UserTypeEnum userType) {
//        var activeUser = userRepository.findByLoginAndUserType(rq.getPhoneNumber(), userType);
//
//        if (activeUser.isPresent() && !activeUser.get().getIsActive()) {
//            throw new ServerException(ErrorCode.INACTIVE_USER, "Login Fail. Your account is inactive. Please contact admin for assistance.");
//        }
//
//        return activeUser.map(user -> new CheckAccountResponse(true))
//                .orElseGet(() -> new CheckAccountResponse(false));
//    }
//
//
//    public AuthenticationResponse refreshToken(RefreshTokenRequest request, Constants.UserTypeEnum userTypeEnum) {
//        return super.refreshToken(request, userTypeEnum);
//    }
}

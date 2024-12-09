package org.se06203.campusexpensemanagement.service;

import lombok.extern.slf4j.Slf4j;
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
}

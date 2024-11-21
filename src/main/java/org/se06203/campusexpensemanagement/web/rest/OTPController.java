package org.se06203.campusexpensemanagement.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.se06203.campusexpensemanagement.config.response.ResponseWrapper;
import org.se06203.campusexpensemanagement.dto.request.RequiredOTPRequest;
import org.se06203.campusexpensemanagement.dto.request.VerifyEmailRequest;
import org.se06203.campusexpensemanagement.dto.request.VerifyOtpRequest;
import org.se06203.campusexpensemanagement.dto.response.VerifyResponse;
import org.se06203.campusexpensemanagement.service.OTPService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("patientOTPController")
@RequestMapping("/api/users/otp")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class OTPController {

    private final OTPService otpService;

    @PostMapping("/request")
    @Operation(tags = "user - OTP")
    public ResponseWrapper<RequiredOTPRequest> requestOTP(@Validated @RequestBody VerifyEmailRequest rq) {
        return ResponseWrapper.success(otpService.generateOTP(rq));
    }

    @PostMapping("/verify")
    @Operation(tags = "user - OTP")
    public ResponseWrapper<VerifyResponse> verifyOTP(@RequestBody VerifyOtpRequest rq) {
        return ResponseWrapper.success(otpService.verifyOTP(rq));
    }
}

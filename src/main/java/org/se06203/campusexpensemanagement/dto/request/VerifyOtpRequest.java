package org.se06203.campusexpensemanagement.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyOtpRequest extends RequiredOTPRequest{
    @NotNull
    private String otp;
}

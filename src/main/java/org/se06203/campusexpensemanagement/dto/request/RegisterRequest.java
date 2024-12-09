package org.se06203.campusexpensemanagement.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.se06203.campusexpensemanagement.utils.validation.ValidEmail;
import org.se06203.campusexpensemanagement.utils.validation.ValidPhoneNumber;

@Getter
@Setter
@Builder
public class RegisterRequest extends RequiredOTPRequest{

    @NotNull
    @ValidEmail
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String userName;

    @ValidPhoneNumber
    private String phoneNumber;
}

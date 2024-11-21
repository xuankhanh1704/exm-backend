package org.se06203.campusexpensemanagement.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.se06203.campusexpensemanagement.utils.validation.ValidEmail;

@Getter
@Setter
public class VerifyEmailRequest {
    @NotNull
    @ValidEmail
    private String email;
}

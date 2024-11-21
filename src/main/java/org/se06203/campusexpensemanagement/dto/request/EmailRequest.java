package org.se06203.campusexpensemanagement.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.se06203.campusexpensemanagement.utils.validation.ValidEmail;
import org.se06203.campusexpensemanagement.utils.validation.ValidPasscode;

@Data
@Builder
public class EmailRequest {

    @NonNull
    @ValidEmail
    private String email;

    @NonNull
    @ValidPasscode
    private String password;
}

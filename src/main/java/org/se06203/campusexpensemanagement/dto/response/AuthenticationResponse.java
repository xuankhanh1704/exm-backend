package org.se06203.campusexpensemanagement.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class AuthenticationResponse {
    private String token;
    private String refreshToken;
}

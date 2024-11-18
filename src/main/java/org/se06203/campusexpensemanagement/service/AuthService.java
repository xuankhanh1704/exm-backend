package org.se06203.campusexpensemanagement.service;

import lombok.RequiredArgsConstructor;
import org.se06203.campusexpensemanagement.dto.request.EmailRequest;
import org.se06203.campusexpensemanagement.dto.response.AuthenticationResponse;
import org.se06203.campusexpensemanagement.utils.Constants;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    public AuthenticationResponse authenticate(EmailRequest request, Constants.role role) {
        return null;
    }
}

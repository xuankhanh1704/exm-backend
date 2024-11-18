package org.se06203.campusexpensemanagement.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.se06203.campusexpensemanagement.config.response.ResponseWrapper;
import org.se06203.campusexpensemanagement.dto.request.EmailRequest;
import org.se06203.campusexpensemanagement.dto.response.AuthenticationResponse;
import org.se06203.campusexpensemanagement.service.AuthService;
import org.se06203.campusexpensemanagement.utils.Constants;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("patientAuthenticateController")
@RequestMapping("/api/patient/authentication")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
public class AuthController {

    private final AuthService authService;
    @Operation(
            summary = "Authenticate user by phone",
            description = "desc",
            tags = "Patient - Authentication"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "failed"),
            @ApiResponse(responseCode = "404", description = "failed")})
    @PostMapping
    public ResponseWrapper<AuthenticationResponse> authenticate(@Validated @RequestBody EmailRequest request) {
        return ResponseWrapper.success(authService.authenticate(request, Constants.role.USER));
    }
}

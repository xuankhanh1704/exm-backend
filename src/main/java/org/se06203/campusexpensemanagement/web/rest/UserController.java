package org.se06203.campusexpensemanagement.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.se06203.campusexpensemanagement.config.response.ResponseWrapper;
import org.se06203.campusexpensemanagement.dto.request.UpdateUserRequest;
import org.se06203.campusexpensemanagement.persistence.entity.Users;
import org.se06203.campusexpensemanagement.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/account")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;


    @PutMapping("/update")
    @Operation(tags = "User - Users")
    public ResponseWrapper<Users> updateUser(@RequestBody UpdateUserRequest request) {
        Users user = userService.updateUser(request);
        return ResponseWrapper.success(user);
    }
}

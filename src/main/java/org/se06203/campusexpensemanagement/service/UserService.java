package org.se06203.campusexpensemanagement.service;

import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.se06203.campusexpensemanagement.config.security.SecurityUtils;
import org.se06203.campusexpensemanagement.dto.request.UpdateUserRequest;
import org.se06203.campusexpensemanagement.persistence.entity.Users;
import org.se06203.campusexpensemanagement.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Users updateUser(UpdateUserRequest updateUserRequest) {
        var userId = SecurityUtils.getAuthenticatedUser().getId();
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        var userUpdate = Users.builder()
                .id(userId)
                .firstName(updateUserRequest.getFirstName())
                .lastName(updateUserRequest.getLastName())
                .userName(updateUserRequest.getUserName())
                .password(updateUserRequest.getPassword())
                .build();
        return userRepository.save(userUpdate);
    }
}

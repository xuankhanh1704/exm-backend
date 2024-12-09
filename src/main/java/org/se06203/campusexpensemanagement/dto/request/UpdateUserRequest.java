package org.se06203.campusexpensemanagement.dto.request;

import jakarta.persistence.Column;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserRequest {

    private String userName;

    private String password;

    private String firstName;

    private String lastName;


}

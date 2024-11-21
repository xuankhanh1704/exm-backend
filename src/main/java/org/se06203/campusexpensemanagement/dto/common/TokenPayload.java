package org.se06203.campusexpensemanagement.dto.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenPayload {
    private String firstName;
    private String lastName;
    private String email;
}

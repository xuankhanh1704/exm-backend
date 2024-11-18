package org.se06203.campusexpensemanagement.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class EmailRequest {

    @NonNull
    private String email;

    @NonNull
    private String password;
}

package org.se06203.campusexpensemanagement.dto.request;

import jakarta.annotation.Nullable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBankRequest {
    private String bankName;

    @Nullable
    private String accountType;

    private String accountNumber;

    private Double amount;
}

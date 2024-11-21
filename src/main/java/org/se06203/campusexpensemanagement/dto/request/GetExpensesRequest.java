package org.se06203.campusexpensemanagement.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.se06203.campusexpensemanagement.persistence.entity.Expenses;
import org.se06203.campusexpensemanagement.persistence.entity.Users;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetExpensesRequest {
    @NotNull
    private Users usersId;

    @NotNull
    private Instant date;

    private Expenses expensesId;
}

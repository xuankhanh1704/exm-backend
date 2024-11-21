package org.se06203.campusexpensemanagement.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.catalina.User;
import org.se06203.campusexpensemanagement.persistence.entity.Categories;
import org.se06203.campusexpensemanagement.persistence.entity.Transactions;
import org.se06203.campusexpensemanagement.persistence.entity.Users;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateExpensesRequest {
    private String name;

    @NotNull
    private Users userId;

    private Double amount;

    private String description;

    @NotNull
    private Categories categoryId;

    @NotNull
    private Transactions transactionId;

    private Instant date;
}

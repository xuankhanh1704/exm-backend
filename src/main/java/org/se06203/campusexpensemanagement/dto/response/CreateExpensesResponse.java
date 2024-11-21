package org.se06203.campusexpensemanagement.dto.response;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.se06203.campusexpensemanagement.persistence.entity.Categories;
import org.se06203.campusexpensemanagement.persistence.entity.Transactions;
import org.se06203.campusexpensemanagement.persistence.entity.Users;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateExpensesResponse {

//    private Long id;

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

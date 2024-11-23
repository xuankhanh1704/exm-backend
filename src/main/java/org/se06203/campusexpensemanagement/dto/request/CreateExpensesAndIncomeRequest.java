package org.se06203.campusexpensemanagement.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.se06203.campusexpensemanagement.persistence.entity.Categories;
import org.se06203.campusexpensemanagement.utils.Constants;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateExpensesAndIncomeRequest {
    @NotNull
    private Long userId;

    private Double amount;

    private String description;

    private Categories categoryId;

    private Constants.PaymentMethod paymentMethod;

    private Instant date;

    private Constants.status status;

}

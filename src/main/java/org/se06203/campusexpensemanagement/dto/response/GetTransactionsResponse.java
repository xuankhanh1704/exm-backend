package org.se06203.campusexpensemanagement.dto.response;

import lombok.*;
import org.se06203.campusexpensemanagement.persistence.entity.Categories;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTransactionsResponse {
    private Double amount;

    private String description;

    private Categories categoryId;

    private Instant date;
}

package org.se06203.campusexpensemanagement.dto.request;

import lombok.*;
import org.se06203.campusexpensemanagement.persistence.entity.Categories;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBudgetRequest {

    private Categories categoryId;

    private Long userId;

    private Instant startDate;

    private Instant endDate;

    private Double amount;

}
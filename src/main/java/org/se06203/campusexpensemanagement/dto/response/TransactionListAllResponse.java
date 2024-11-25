package org.se06203.campusexpensemanagement.dto.response;

import lombok.Builder;
import lombok.Data;
import org.se06203.campusexpensemanagement.persistence.entity.Categories;

import java.time.Instant;

@Data
@Builder
public class TransactionListAllResponse {
    private Long id;
    private Categories categories;
    private String description;
    private Instant date;
    private Double amount;
}

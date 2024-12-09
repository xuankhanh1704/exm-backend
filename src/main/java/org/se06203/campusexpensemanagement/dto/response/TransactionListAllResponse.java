package org.se06203.campusexpensemanagement.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class TransactionListAllResponse {
    private Long id;
    private Long categoryId;
    private String categoryName;
    private String description;
    private Instant date;
    private Double amount;
}

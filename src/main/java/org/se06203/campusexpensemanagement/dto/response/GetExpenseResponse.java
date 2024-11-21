package org.se06203.campusexpensemanagement.dto.response;
import jdk.jfr.Category;
import lombok.*;
import org.se06203.campusexpensemanagement.persistence.entity.Categories;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetExpenseResponse {

//    private String name;

    private Double amount;

    private String description;

    private Categories categoryId;

    private Instant date;
}

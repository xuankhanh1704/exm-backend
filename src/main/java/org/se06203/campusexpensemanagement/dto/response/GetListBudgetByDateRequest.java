package org.se06203.campusexpensemanagement.dto.response;

import lombok.*;
import org.se06203.campusexpensemanagement.persistence.entity.Categories;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetListBudgetByDateRequest {

//    private Long userId;

    private Instant date;

    private Double amount;

    private Categories categoryId;




}

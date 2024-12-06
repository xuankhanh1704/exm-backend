package org.se06203.campusexpensemanagement.dto.response;

import lombok.*;
import org.se06203.campusexpensemanagement.persistence.entity.Banks;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetListBankResponse {

    private String bankName;

    private Double amount;
}

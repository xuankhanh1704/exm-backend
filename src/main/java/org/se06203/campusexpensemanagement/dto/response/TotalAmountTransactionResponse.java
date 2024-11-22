package org.se06203.campusexpensemanagement.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TotalAmountTransactionResponse {
    private int totalIncome;
    private int totalExpense;
    private int totalAmount;
}

package org.se06203.campusexpensemanagement.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.se06203.campusexpensemanagement.config.response.ResponseWrapper;
import org.se06203.campusexpensemanagement.dto.request.CreateExpensesAndIncomeRequest;
import org.se06203.campusexpensemanagement.dto.response.TotalAmountTransactionResponse;
import org.se06203.campusexpensemanagement.dto.response.TransactionListAllResponse;
import org.se06203.campusexpensemanagement.service.TransactionService;
import org.se06203.campusexpensemanagement.utils.Constants;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("userTransactionController")
@RequestMapping("/api/users/transaction")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @Operation(tags = "User - Transaction")
    public ResponseWrapper<Void> createExpenses(@RequestBody CreateExpensesAndIncomeRequest request) {
        transactionService.createIncomeAndExpenses(request);
        return ResponseWrapper.success();
    }

    @GetMapping
    @Operation(tags = "User - Transaction")
    public ResponseWrapper<List<TransactionListAllResponse>> getAllExpenses() {
        return ResponseWrapper.success(transactionService.getAllTransactions());
    }

    @GetMapping("/total")
    @Operation(tags = "User - Transaction")
    public ResponseWrapper<TotalAmountTransactionResponse> getTotalAmount (){
        return ResponseWrapper.success(transactionService.totalAmount());
    }

    @GetMapping("/list")
    @Operation(tags = "User - Transaction")
    public ResponseWrapper<List<TransactionListAllResponse>> getListAll(@RequestParam(value = "type",required = false) Constants.PaymentMethod paymentMethod){
        return ResponseWrapper.success(transactionService.getAll(paymentMethod));
    }
}

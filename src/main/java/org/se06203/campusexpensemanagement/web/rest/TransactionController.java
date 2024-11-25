package org.se06203.campusexpensemanagement.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.se06203.campusexpensemanagement.config.response.ResponseWrapper;
import org.se06203.campusexpensemanagement.dto.response.TotalAmountTransactionResponse;
import org.se06203.campusexpensemanagement.dto.response.TransactionListAllResponse;
import org.se06203.campusexpensemanagement.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseWrapper<List<GetTransactionsResponse>> getAllExpenses(@RequestParam(value = "date") Instant date) {
        List<GetTransactionsResponse> transactionsResponses = transactionService.getAllTransactions(date);
        return ResponseWrapper.success(transactionsResponses);
    }

    @GetMapping()
    @GetMapping("/total")
    @Operation(tags = "User - Transaction")
    public ResponseWrapper<TotalAmountTransactionResponse> getTotalAmount (){
        return ResponseWrapper.success(transactionService.totalAmount());
    }

    @GetMapping()
    @Operation(tags = "User - Transaction")
    public ResponseWrapper<List<TransactionListAllResponse>> getListAll(){
        return ResponseWrapper.success(transactionService.getAll());
    }

    @GetMapping("/")
    @Operation(tags = "User - Transaction" )
    public ResponseWrapper<List<GetTransactionsResponse>> getTransactionsResponseList(@RequestParam(value = "type") Constants.PaymentMethod paymentMethod) {
        List<GetTransactionsResponse> listTransactions = transactionService.getTransactionsResponseList(paymentMethod);
        return ResponseWrapper.success(listTransactions);
    }

}

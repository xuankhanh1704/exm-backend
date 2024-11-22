package org.se06203.campusexpensemanagement.web.rest;

import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.se06203.campusexpensemanagement.config.response.ResponseWrapper;
import org.se06203.campusexpensemanagement.dto.request.CreateExpensesRequest;
import org.se06203.campusexpensemanagement.dto.response.GetTransactionsResponse;
import org.se06203.campusexpensemanagement.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseWrapper<Void> createExpenses(@RequestBody CreateExpensesRequest request) {
        transactionService.createExpenses(request);
        return ResponseWrapper.success();
    }
    @GetMapping
    public ResponseWrapper<List<GetTransactionsResponse>> getAllExpenses(@RequestParam(value ="userId")Long userId,
                                                                         @RequestParam(value = "date") Instant date) {
        List<GetTransactionsResponse> transactionsResponses = transactionService.getAllTransactions(userId,date);
        return ResponseWrapper.success(transactionsResponses);
    }

}

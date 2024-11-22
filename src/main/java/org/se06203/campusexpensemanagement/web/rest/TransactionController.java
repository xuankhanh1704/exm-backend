package org.se06203.campusexpensemanagement.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Getter;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.se06203.campusexpensemanagement.config.response.ResponseWrapper;
import org.se06203.campusexpensemanagement.dto.request.CreateExpensesRequest;
import org.se06203.campusexpensemanagement.dto.response.GetTransactionsResponse;
import org.se06203.campusexpensemanagement.dto.response.TotalAmountTransactionResponse;
import org.se06203.campusexpensemanagement.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RestController("userTransactionController")
@RequestMapping("/api/users/transaction")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseWrapper<Void> createExpenses(@RequestBody CreateExpensesRequest request) {
        transactionService.createExpenses(request);
        return ResponseWrapper.success();
    @GetMapping()
    @Operation(tags = "User - Transaction")
    public ResponseWrapper<TotalAmountTransactionResponse> getTotalAmount (){
        return ResponseWrapper.success(transactionService.totalAmount());
    }

    @GetMapping
    public ResponseWrapper<List<GetTransactionsResponse>> getAllExpenses(@RequestParam(value ="userId")Long userId,
                                                                         @RequestParam(value = "date") Instant date) {
        List<GetTransactionsResponse> transactionsResponses = transactionService.getAllTransactions(userId,date);
        return ResponseWrapper.success(transactionsResponses);
    }


}

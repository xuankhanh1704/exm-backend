package org.se06203.campusexpensemanagement.web.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.se06203.campusexpensemanagement.config.response.ResponseWrapper;
import org.se06203.campusexpensemanagement.dto.request.CreateExpensesRequest;
import org.se06203.campusexpensemanagement.dto.request.GetExpensesRequest;
import org.se06203.campusexpensemanagement.dto.response.GetExpenseResponse;
import org.se06203.campusexpensemanagement.persistence.entity.Expenses;
import org.se06203.campusexpensemanagement.service.ExpensesService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ExpensesController {

    private final ExpensesService expensesService;

    @PostMapping
    public ResponseWrapper<Void> createExpenses(@RequestBody CreateExpensesRequest rq) {
        expensesService.createExpenses(rq);
        return ResponseWrapper.success();
    }

    @GetMapping
    public ResponseWrapper<List<GetExpenseResponse>> getAllExpenses(@RequestParam(value ="userId")Long userId,
                                                                    @RequestParam(value = "date") Instant date) {
        List<GetExpenseResponse> expensesResponse = expensesService.getAllExpenses(userId,date);
        return ResponseWrapper.success(expensesResponse);
    }
}

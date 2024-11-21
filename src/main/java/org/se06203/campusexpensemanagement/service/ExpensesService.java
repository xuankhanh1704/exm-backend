package org.se06203.campusexpensemanagement.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.se06203.campusexpensemanagement.config.security.SecurityUtils;
import org.se06203.campusexpensemanagement.dto.request.CreateExpensesRequest;
import org.se06203.campusexpensemanagement.dto.request.GetExpensesRequest;
import org.se06203.campusexpensemanagement.dto.response.GetExpenseResponse;
import org.se06203.campusexpensemanagement.persistence.entity.Expenses;
import org.se06203.campusexpensemanagement.persistence.repository.ExpenseRepository;
import org.se06203.campusexpensemanagement.utils.mapper.ExpensesMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpensesService {

    public  final ExpenseRepository expenseRepository;
    public final SecurityUtils securityUtils;
    public final ExpensesMapper expensesMapper;

    @Transactional
    public void createExpenses(CreateExpensesRequest request) {
        var expenses = expensesMapper.mapRequestToCreateExpensesRequest(request);
        expenseRepository.save(expenses);
    }

    @Transactional
    public List<GetExpenseResponse> getAllExpenses(Long userId, Instant date) {
        return expenseRepository.findAllByUserIdAndDate(userId,date).stream().map(
                data -> GetExpenseResponse.builder()
                        .amount(data.getAmount())
                        .description(data.getDescription())
                        .date(data.getDate())
                        .categoryId(data.getCategory())
                        .build()
        ).toList();
    }

//    @Transactional
//    public GetExpenseResponse getExpense(Long userId) {
//        expenseRepository.findById().ifPresent(expense -> {
//
//        });
//    }

}

package org.se06203.campusexpensemanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.se06203.campusexpensemanagement.config.exception.NotFoundException;
import org.se06203.campusexpensemanagement.config.security.SecurityUtils;
import org.se06203.campusexpensemanagement.dto.response.TotalAmountTransactionResponse;
import org.se06203.campusexpensemanagement.dto.response.TransactionListAllResponse;
import org.se06203.campusexpensemanagement.persistence.repository.TransactionRepository;
import org.se06203.campusexpensemanagement.utils.mapper.TransactionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    public TotalAmountTransactionResponse totalAmount() {
        var userId = SecurityUtils.getAuthenticatedUser().getId();
        var totalExpense = transactionRepository.findTotalExpenseByUserId(userId)
                .orElseThrow(() -> new NotFoundException("cannot find userId"));
        var totalIncome = transactionRepository.findTotalIncomeByUserId(userId)
                .orElseThrow(() -> new NotFoundException("cannot find userId"));
        var totalAmount = totalIncome.getTotal() - totalExpense.getTotal();

        return TotalAmountTransactionResponse.builder()
                .totalIncome(totalIncome.getTotal())
                .totalExpense(totalExpense.getTotal())
                .totalAmount(totalAmount)
                .build();
    }

    public List<TransactionListAllResponse> getAll() {
        var userId = SecurityUtils.getAuthenticatedUser().getId();
        return transactionRepository.findAllByUserId(userId).stream()
                .map(transactionMapper::mapToTransactionListAll).toList();
    }
}

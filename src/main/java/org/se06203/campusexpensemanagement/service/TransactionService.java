package org.se06203.campusexpensemanagement.service;

import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.se06203.campusexpensemanagement.config.security.SecurityUtils;
import org.se06203.campusexpensemanagement.dto.request.CreateExpensesRequest;
import org.se06203.campusexpensemanagement.dto.response.GetTransactionsResponse;
import org.se06203.campusexpensemanagement.persistence.entity.Transactions;
import org.se06203.campusexpensemanagement.persistence.repository.TransactionRepository;
import org.se06203.campusexpensemanagement.persistence.repository.UserRepository;
import org.se06203.campusexpensemanagement.utils.Constants;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final SecurityUtils securityUtils;
    private final UserRepository userRepository;



    @Transactional
    public void createExpenses(CreateExpensesRequest request) {
        var userId = SecurityUtils.getAuthenticatedUser().getId();

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        var expenses = Transactions.builder()
                .user(user)
                .description(request.getDescription())
                .amount(request.getAmount())
                .category(request.getCategoryId())
                .date(request.getDate())
                .paymentMethod(Constants.PaymentMethod.EXPENSE)
                .build();
        transactionRepository.save(expenses);
    }
    @Transactional
    public List<GetTransactionsResponse> getAllTransactions(Long userId, Instant date) {
        return transactionRepository.findAllByUserIdAndDate(userId,date).stream().map(
                data -> GetTransactionsResponse.builder()
                        .amount(data.getAmount())
                        .description(data.getDescription())
                        .date(data.getDate())
                        .categoryId(data.getCategory())
                        .build()
        ).toList();
    }

}

package org.se06203.campusexpensemanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.se06203.campusexpensemanagement.config.exception.NotFoundException;
import org.se06203.campusexpensemanagement.config.security.SecurityUtils;
import org.se06203.campusexpensemanagement.dto.request.CreateExpensesAndIncomeRequest;
import org.se06203.campusexpensemanagement.dto.response.GetTransactionsResponse;
import org.se06203.campusexpensemanagement.persistence.entity.Transactions;
import org.se06203.campusexpensemanagement.dto.response.TotalAmountTransactionResponse;
import org.se06203.campusexpensemanagement.dto.response.TransactionListAllResponse;
import org.se06203.campusexpensemanagement.persistence.repository.TransactionRepository;
import org.se06203.campusexpensemanagement.utils.mapper.TransactionMapper;
import org.se06203.campusexpensemanagement.persistence.repository.UserRepository;
import org.se06203.campusexpensemanagement.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;



    @Transactional
    public void createIncomeAndExpenses(CreateExpensesAndIncomeRequest request) {
        var userId = SecurityUtils.getAuthenticatedUser().getId();

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with userId: " + userId));
    private final TransactionMapper transactionMapper;

        var expenses = Transactions.builder()
                .user(user)
                .description(request.getDescription())
                .amount(request.getAmount())
                .category(request.getCategoryId())
                .date(request.getDate())
                .paymentMethod(request.getPaymentMethod())
                .build();
        transactionRepository.save(expenses);
    }

    @Transactional
    public List<GetTransactionsResponse> getAllTransactions(Instant date) {
        var userId = SecurityUtils.getAuthenticatedUser().getId();
        return transactionRepository.findAllByUserIdAndDate(userId,date).stream().map(
                data -> GetTransactionsResponse.builder()
                        .amount(data.getAmount())
                        .description(data.getDescription())
                        .date(data.getDate())
                        .categoryId(data.getCategory())
                        .build()
        ).toList();
    }

    @Transactional
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

    @Transactional
    public List<GetTransactionsResponse> getTransactionsResponseList(Constants.PaymentMethod paymentMethod) {
        var userId = SecurityUtils.getAuthenticatedUser().getId();
        return transactionRepository.findAllByUserIdAndType(userId,paymentMethod).stream().map(
                data -> GetTransactionsResponse.builder()
                        .amount(data.getAmount())
                        .description(data.getDescription())
                        .date(data.getDate())
                        .categoryId(data.getCategory())
                        .build()
        ).toList();
    }

}


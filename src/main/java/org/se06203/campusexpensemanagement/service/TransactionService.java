package org.se06203.campusexpensemanagement.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.se06203.campusexpensemanagement.config.ConvertToDate;
import org.se06203.campusexpensemanagement.config.exception.NotFoundException;
import org.se06203.campusexpensemanagement.config.security.SecurityUtils;
import org.se06203.campusexpensemanagement.dto.request.CreateExpensesAndIncomeRequest;
import org.se06203.campusexpensemanagement.dto.response.TotalAmountTransactionResponse;
import org.se06203.campusexpensemanagement.dto.response.TransactionListAllResponse;
import org.se06203.campusexpensemanagement.persistence.repository.BankRepository;
import org.se06203.campusexpensemanagement.persistence.repository.CategoryRepository;
import org.se06203.campusexpensemanagement.persistence.repository.TransactionRepository;
import org.se06203.campusexpensemanagement.utils.mapper.TransactionMapper;
import org.se06203.campusexpensemanagement.persistence.repository.UserRepository;
import org.se06203.campusexpensemanagement.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final TransactionMapper transactionMapper;
    private final CategoryRepository categoryRepository;
    private final BankRepository bankRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, TransactionMapper transactionMapper, CategoryRepository categoryRepository, BankRepository bankRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.transactionMapper = transactionMapper;
        this.categoryRepository = categoryRepository;
        this.bankRepository = bankRepository;
    }

    @Transactional
    public void createIncomeAndExpenses(CreateExpensesAndIncomeRequest request) {
        var userId = SecurityUtils.getAuthenticatedUser().getId();
        var category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("cannot found:", request.getCategoryId().toString()));
        var bank = bankRepository.findById(request.getBankId())
                .orElseThrow(() -> new NotFoundException("not found bank", request.getBankId().toString()));

        userRepository.findById(userId)
                .ifPresentOrElse(data -> {
                            var transaction = transactionMapper.mapToTransaction(request, data, category, bank);
                            transactionRepository.save(transaction);
                        },
                        () -> {
                            throw new NotFoundException(userId.toString());
                        });
    }

    public List<TransactionListAllResponse> getAllTransactions() {
        var lastDay = ConvertToDate.lastDayOfMonth();
        var firstDay = ConvertToDate.firstDayOfMonth();
        var userId = SecurityUtils.getAuthenticatedUser().getId();

        return transactionRepository.findAllByUserIdAndDate(userId, lastDay, firstDay).stream()
                .map(transactionMapper::mapToTransactionListAll).toList();
    }

    public TotalAmountTransactionResponse totalAmount() {
        var userId = SecurityUtils.getAuthenticatedUser().getId();
        var totalExpense = transactionRepository.findTotalExpenseByUserId(userId)
                .orElseThrow(() -> new NotFoundException("cannot find userId"));
        var totalIncome = transactionRepository.findTotalIncomeByUserId(userId)
                .orElseThrow(() -> new NotFoundException("cannot find userId"));
        var totalAmount = totalIncome.getTotal() - totalExpense.getTotal();

        return transactionMapper.mapToTransactionTotal(totalIncome,totalAmount,totalExpense);
    }

    public List<TransactionListAllResponse> getAll(Constants.PaymentMethod paymentMethod) {
        var userId = SecurityUtils.getAuthenticatedUser().getId();

        if (paymentMethod != null) {
            return transactionRepository.findAllByUserIdAndType(userId, paymentMethod).stream()
                    .map(transactionMapper::mapToTransactionListAll).toList();
        } else {
            return transactionRepository.findAllByUserId(userId).stream()
                    .map(transactionMapper::mapToTransactionListAll).toList();
        }
    }
}


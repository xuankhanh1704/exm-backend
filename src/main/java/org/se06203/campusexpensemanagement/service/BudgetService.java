package org.se06203.campusexpensemanagement.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.se06203.campusexpensemanagement.config.security.SecurityUtils;
import org.se06203.campusexpensemanagement.dto.request.CreateBudgetRequest;
import org.se06203.campusexpensemanagement.persistence.entity.Budgets;
import org.se06203.campusexpensemanagement.persistence.repository.BudgetRepository;
import org.se06203.campusexpensemanagement.persistence.repository.UserRepository;
import org.se06203.campusexpensemanagement.utils.Constants;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;


@Service
@Slf4j
public class BudgetService {

    private final UserRepository userRepository;
    private final BudgetRepository budgetRepository;

    public BudgetService(UserRepository userRepository, BudgetRepository budgetRepository) {
        this.userRepository = userRepository;
        this.budgetRepository = budgetRepository;
    }

    @Transactional
    public void createBudget(CreateBudgetRequest request){

        var userId = SecurityUtils.getAuthenticatedUser().getId();

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        var now = Instant.now();
        var currentDateTime = now.atZone(ZoneId.systemDefault());

        var budget = Budgets.builder()
                .user(user)
                .amount(request.getAmount())
                .category(request.getCategoryId())
                .startDate(currentDateTime.toInstant())
                .endDate(currentDateTime
                        .withDayOfMonth(currentDateTime.toLocalDate().lengthOfMonth())
                        .toInstant())
                .status(Constants.status.ACTIVE)
                .build();
        budgetRepository.save(budget);
    }


    @Transactional
    public void deleteBudget(Long budgetId){
        var userId = SecurityUtils.getAuthenticatedUser().getId();
        var delete = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new RuntimeException("Budget not found"));

        budgetRepository.delete(delete);
    }

//    @Transactional
//    public List<Budgets> getAllBudgetsByDate(){
//
//    }

}

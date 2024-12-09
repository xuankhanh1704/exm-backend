package org.se06203.campusexpensemanagement.web.rest;

import lombok.RequiredArgsConstructor;
import org.geolatte.geom.V;
import org.se06203.campusexpensemanagement.config.response.ResponseWrapper;
import org.se06203.campusexpensemanagement.dto.request.CreateBudgetRequest;
import org.se06203.campusexpensemanagement.service.BudgetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/budgets")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @PostMapping
    public ResponseWrapper<Void> createNewBudget (@RequestBody CreateBudgetRequest request) {
        budgetService.createBudget(request);
        return  ResponseWrapper.success();
    }
}

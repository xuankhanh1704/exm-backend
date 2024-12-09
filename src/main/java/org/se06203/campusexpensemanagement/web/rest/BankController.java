package org.se06203.campusexpensemanagement.web.rest;

import lombok.RequiredArgsConstructor;
import org.se06203.campusexpensemanagement.config.response.ResponseWrapper;
import org.se06203.campusexpensemanagement.dto.request.CreateBankRequest;
import org.se06203.campusexpensemanagement.dto.response.GetListBankResponse;
import org.se06203.campusexpensemanagement.service.BankService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user/bank")
@RequiredArgsConstructor
public class BankController {


    private final BankService bankService;

    @GetMapping
    public ResponseWrapper<List<GetListBankResponse>> getListBank() {
        List<GetListBankResponse> responseList = bankService.getAllBanks();
        return ResponseWrapper.success(responseList);
    }


    @PostMapping
    public ResponseWrapper<Void> createBank(@RequestBody CreateBankRequest request) {
        bankService.createBank(request);
        return ResponseWrapper.success();
    }
}

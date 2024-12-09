package org.se06203.campusexpensemanagement.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.se06203.campusexpensemanagement.config.security.SecurityUtils;
import org.se06203.campusexpensemanagement.dto.request.CreateBankRequest;
import org.se06203.campusexpensemanagement.dto.response.GetListBankResponse;
import org.se06203.campusexpensemanagement.persistence.entity.Banks;
import org.se06203.campusexpensemanagement.persistence.repository.BankRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BankService {

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Transactional
    public List<GetListBankResponse> getAllBanks() {
        var userId = SecurityUtils.getAuthenticatedUser().getId();

        return bankRepository.findAll().stream().map(
                bank -> GetListBankResponse.builder()
                        .bankName(bank.getName())
                        .amount(bank.getAmount())
                        .build()
        ).toList();
    }

    @Transactional
    public void createBank(CreateBankRequest request) {
        var banks = Banks.builder()
                .name(request.getBankName())
                .accountNumber(request.getAccountNumber())
                .accountType(request.getAccountType())
                .amount(request.getAmount())
                .build();
        bankRepository.save(banks);
    }
}

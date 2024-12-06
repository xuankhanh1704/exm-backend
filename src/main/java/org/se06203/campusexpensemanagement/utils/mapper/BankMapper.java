package org.se06203.campusexpensemanagement.utils.mapper;

import org.se06203.campusexpensemanagement.dto.request.CreateBankRequest;
import org.se06203.campusexpensemanagement.persistence.entity.Banks;

public interface BankMapper {
    CreateBankRequest maptoCreateBankRequest(Banks banks);
    Banks maptoBankRequest(CreateBankRequest request);
}

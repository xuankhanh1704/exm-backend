package org.se06203.campusexpensemanagement.utils.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.se06203.campusexpensemanagement.dto.response.TransactionListAllResponse;
import org.se06203.campusexpensemanagement.persistence.entity.Transactions;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CategoryMapper.class)
public interface TransactionMapper {

    default TransactionListAllResponse mapToTransactionListAll(Transactions transactions) {
        TransactionListAllResponse.TransactionListAllResponseBuilder transactionListAllResponse = TransactionListAllResponse.builder();

        transactionListAllResponse.id(transactions.getId());
        transactionListAllResponse.description(transactions.getDescription());
        transactionListAllResponse.date(transactions.getDate());
        transactionListAllResponse.amount(transactions.getAmount());
        transactionListAllResponse.categories(transactions.getCategory());

        return transactionListAllResponse.build();
    }
}

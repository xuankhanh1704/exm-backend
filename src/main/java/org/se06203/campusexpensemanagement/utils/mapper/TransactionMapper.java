package org.se06203.campusexpensemanagement.utils.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.se06203.campusexpensemanagement.dto.request.CreateExpensesAndIncomeRequest;
import org.se06203.campusexpensemanagement.dto.response.TotalAmountTransactionResponse;
import org.se06203.campusexpensemanagement.dto.response.TransactionListAllResponse;
import org.se06203.campusexpensemanagement.persistence.entity.Banks;
import org.se06203.campusexpensemanagement.persistence.entity.Categories;
import org.se06203.campusexpensemanagement.persistence.entity.Transactions;
import org.se06203.campusexpensemanagement.persistence.entity.Users;
import org.se06203.campusexpensemanagement.persistence.entity.projection.TotalAmountTransactionProjection;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = CategoryMapper.class)
public interface TransactionMapper {

    @Mapping(source = "transactions.category.id", target = "categoryId")
    @Mapping(source = "transactions.category.name", target = "categoryName")
    TransactionListAllResponse mapToTransactionListAll(Transactions transactions);

    default Transactions mapToTransaction(CreateExpensesAndIncomeRequest request,
                                          Users data, Categories category, Banks bank){
        return new Transactions().toBuilder()
                .amount(request.getAmount())
                .bank(bank)
                .user(data)
                .date(request.getDate())
                .category(category)
                .description(request.getDescription())
                .paymentMethod(request.getPaymentMethod())
                .status(request.getStatus())
                .build();
    }

    default TotalAmountTransactionResponse mapToTransactionTotal(TotalAmountTransactionProjection totalIncome,
                                                         int totalAmount,
                                                         TotalAmountTransactionProjection totalExpense){
        return TotalAmountTransactionResponse.builder()
                .totalIncome(totalIncome.getTotal())
                .totalExpense(totalExpense.getTotal())
                .totalAmount(totalAmount)
                .build();
    }
}
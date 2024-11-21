package org.se06203.campusexpensemanagement.utils.mapper;
import org.mapstruct.Mapper;
import org.se06203.campusexpensemanagement.dto.request.CreateExpensesRequest;
import org.se06203.campusexpensemanagement.persistence.entity.Expenses;


@Mapper
public interface ExpensesMapper {
    Expenses mapRequestToCreateExpensesRequest(CreateExpensesRequest request);
}

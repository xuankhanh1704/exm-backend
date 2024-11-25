package org.se06203.campusexpensemanagement.persistence.repository;

import org.se06203.campusexpensemanagement.dto.response.TransactionListAllResponse;
import org.se06203.campusexpensemanagement.persistence.entity.Transactions;
import org.se06203.campusexpensemanagement.persistence.entity.projection.TotalAmountTransactionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    @Query("""
            SELECT SUM(tr.amount) FROM Transactions tr
            WHERE tr.user.id = :userId
            AND tr.paymentMethod = 'EXPENSE'
            """)
    Optional<TotalAmountTransactionProjection> findTotalExpenseByUserId(Long userId);

    @Query("""
            SELECT SUM(tr.amount) FROM Transactions tr
            WHERE tr.user.id = :userId
            AND tr.paymentMethod = 'INCOME'
            """)
    Optional<TotalAmountTransactionProjection> findTotalIncomeByUserId(Long userId);

    @Query("""
            SELECT tr FROM Transactions tr
            WHERE tr.user.id = :userId
            ORDER BY tr.date DESC
            """)
    List<Transactions> findAllByUserId(Long userId);
}

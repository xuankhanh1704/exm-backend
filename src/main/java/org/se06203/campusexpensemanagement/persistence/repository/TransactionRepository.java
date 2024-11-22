package org.se06203.campusexpensemanagement.persistence.repository;

import org.se06203.campusexpensemanagement.persistence.entity.Transactions;
import org.se06203.campusexpensemanagement.persistence.entity.projection.TotalAmountTransactionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findAllByUserIdAndDate(Long userId, Instant date);

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
}

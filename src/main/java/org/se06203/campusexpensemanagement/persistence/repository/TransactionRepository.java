package org.se06203.campusexpensemanagement.persistence.repository;

import org.se06203.campusexpensemanagement.persistence.entity.Transactions;
import org.se06203.campusexpensemanagement.persistence.entity.projection.TotalAmountTransactionProjection;
import org.se06203.campusexpensemanagement.utils.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    @Query("""
            SELECT tr FROM Transactions tr
            WHERE tr.user.id = :userId
            AND tr.date <= :endDate
            AND tr.date >= :firstDate
            """)
    List<Transactions> findAllByUserIdAndDate(Long userId, Instant endDate, Instant firstDate);

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

    @Query("""
            SELECT tr FROM Transactions tr
            WHERE tr.paymentMethod = :paymentMethod
            AND tr.user.id = :userId
            """)
    List<Transactions> findAllByUserIdAndType(Long userId, Constants.PaymentMethod paymentMethod );

}

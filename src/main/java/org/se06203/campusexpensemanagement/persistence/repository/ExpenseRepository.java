package org.se06203.campusexpensemanagement.persistence.repository;

import org.se06203.campusexpensemanagement.persistence.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expenses, Long> {


//
//    @Query("""
//""")
    List<Expenses> findAllByUserIdAndDate(Long userId, Instant date);

    Expenses findByUserIdAnd(Long userId);

}

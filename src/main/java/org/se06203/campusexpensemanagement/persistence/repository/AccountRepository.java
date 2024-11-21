package org.se06203.campusexpensemanagement.persistence.repository;

import org.se06203.campusexpensemanagement.persistence.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {

    List<Accounts> findAllByUserId(Long userId);
}

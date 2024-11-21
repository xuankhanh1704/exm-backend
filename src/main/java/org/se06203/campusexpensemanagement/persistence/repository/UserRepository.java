package org.se06203.campusexpensemanagement.persistence.repository;

import jakarta.validation.constraints.NotNull;
import org.se06203.campusexpensemanagement.persistence.entity.Users;
import org.se06203.campusexpensemanagement.utils.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the {@link Users} entity.
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
    Optional<Users> findByUserName(String login);

    @Query("""
            SELECT u FROM Users u
            JOIN Accounts a
            ON a.user.id = u.id
            WHERE u.userName = :userName
            AND a.role = :role
            """)
    Optional<Users> findByUserNameAndRole(String userName, Constants.role role);

    @Query("""
            SELECT u FROM Users u
            JOIN Accounts a
            ON a.user.id = u.id
            WHERE u.email = :email
            AND a.role = :role
            """)
    Optional<Users> findByEmailAndRole( String email, Constants.role role);
}

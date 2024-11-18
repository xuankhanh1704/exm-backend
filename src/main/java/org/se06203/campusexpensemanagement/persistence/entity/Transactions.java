package org.se06203.campusexpensemanagement.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.se06203.campusexpensemanagement.utils.Constants;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "transactions")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private Constants.PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Banks bank;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private Instant date;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Constants.status status;

    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY)
    private List<Expenses> expenses;

    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY)
    private List<Payments> payment;
}

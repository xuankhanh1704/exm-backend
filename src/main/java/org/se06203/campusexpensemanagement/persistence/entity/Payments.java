package org.se06203.campusexpensemanagement.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.se06203.campusexpensemanagement.utils.Constants;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
@Builder(toBuilder = true)
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transactions transaction;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private Instant date;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private Constants.PaymentMethod paymentMethod;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Constants.status status;
}

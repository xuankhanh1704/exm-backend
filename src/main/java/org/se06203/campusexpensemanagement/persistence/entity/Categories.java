package org.se06203.campusexpensemanagement.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

@Entity
@Table(name = "categories")
@Builder(toBuilder = true)
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Transactions> transactions;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Budgets> budgets;
}

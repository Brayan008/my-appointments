package com.appointment.owner.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "owners_seq", sequenceName = "owners_sequence", allocationSize = 1)
@Table(name = "owners")
@Getter
@Setter
@ToString
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owners_seq")
    @Column(nullable = true)
    private Long ownerId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private CustomersEntity userId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private CompanyEntity companyId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerEntity that = (OwnerEntity) o;
        return Objects.equals(ownerId, that.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ownerId);
    }
}

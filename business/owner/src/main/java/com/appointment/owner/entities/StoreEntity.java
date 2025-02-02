package com.appointment.owner.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "stores_seq", sequenceName = "stores_sequence", allocationSize = 1)
@Table(name = "stores")
@Getter
@Setter
@ToString
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stores_seq")
    @Column(nullable = true)
    private Long storeId;

    private String name;

    @Column(nullable = true, length = 200)
    private String address;

    private String description;

    @Column(nullable = true, length = 400)
    private String coordinates;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private StatusEntity statusId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private CompanyEntity companyId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreEntity that = (StoreEntity) o;
        return Objects.equals(storeId, that.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(storeId);
    }
}

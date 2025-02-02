package com.appointment.owner.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "services_seq", sequenceName = "services_sequence", allocationSize = 1)
@Table(name = "services")
@Getter
@Setter
@ToString
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "services_seq")
    @Column(nullable = true)
    private Long serviceId;
    private String name;

    private BigDecimal price;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private StoreEntity storeId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceEntity that = (ServiceEntity) o;
        return Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(serviceId);
    }
}

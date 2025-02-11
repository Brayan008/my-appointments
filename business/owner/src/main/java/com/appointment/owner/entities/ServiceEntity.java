package com.appointment.owner.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Column(nullable = false)
    private Long serviceId;

    private String name;
    private BigDecimal price;
    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private StoreEntity store;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

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

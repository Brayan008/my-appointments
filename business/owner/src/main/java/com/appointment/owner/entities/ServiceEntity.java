package com.appointment.owner.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(nullable = false, length = 255)
    private String name;

    @Check(constraints = "rating >= 0")
    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "status_id", nullable = false)
    private Long statusId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private StatusEntity status;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private StoreEntity store;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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

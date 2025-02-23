package com.appointment.database.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "client_dates_seq", sequenceName = "client_dates_sequence", allocationSize = 1)
@Table(name = "client_dates")
@Getter
@Setter
@ToString
public class ClientDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_favorite_stores_seq")
    @Column(name = "client_date_id", nullable = false)
    private Long clientDateId;

    @Column(name = "user_date", nullable = false)
    private LocalDateTime userDate;

    @Column(name = "status_date_id", nullable = false)
    private Long statusDateId;

    @Column(name = "service_id", nullable = false)
    private Long serviceId;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDateEntity that = (ClientDateEntity) o;
        return Objects.equals(clientDateId, that.clientDateId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(clientDateId);
    }
}

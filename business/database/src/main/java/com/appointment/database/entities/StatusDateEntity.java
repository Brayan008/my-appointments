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
@SequenceGenerator(name = "status_dates_seq", sequenceName = "status_dates_sequence", allocationSize = 1)
@Table(name = "status_dates")
@Getter
@Setter
@ToString
public class StatusDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_dates_seq")
    @Column(name = "status_date_id", nullable = false)
    private Long statusDateId;

    @Column(nullable = false, length = 300)
    private String name;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusDateEntity that = (StatusDateEntity) o;
        return Objects.equals(statusDateId, that.statusDateId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(statusDateId);
    }
}

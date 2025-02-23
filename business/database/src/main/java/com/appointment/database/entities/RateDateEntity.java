package com.appointment.database.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "rate_date_seq", sequenceName = "rate_date_sequence", allocationSize = 1)
@Table(name = "rate_date")
@Getter
@Setter
@ToString
public class RateDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rate_date_seq")
    @Column(name = "rate_date_id", nullable = false)
    private Long rateDateId;

    @Column(nullable = false, length = 1000)
    private String comment;

    @Check(constraints = "rate >= 0 and rate <=5")
    @Column(nullable = false)
    private Integer rate;

    @Column(name = "client_date_id", nullable = false)
    private Long clientDateId;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateDateEntity that = (RateDateEntity) o;
        return Objects.equals(rateDateId, that.rateDateId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rateDateId);
    }

}

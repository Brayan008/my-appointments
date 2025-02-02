package com.appointment.owner.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "status_seq", sequenceName = "status_sequence", allocationSize = 1)
@Table(name = "status")
@Setter
@Getter
@ToString
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_seq")
    @Column(nullable = true)
    private Long statusId;

    @Column(nullable = true, length = 50)
    private String name;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusEntity that = (StatusEntity) o;
        return Objects.equals(statusId, that.statusId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(statusId);
    }
}

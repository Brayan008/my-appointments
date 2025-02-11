package com.appointment.owner.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Column(nullable = false)
    private Long statusId;

    @Column(nullable = false, length = 50)
    private String name;

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
        StatusEntity that = (StatusEntity) o;
        return Objects.equals(statusId, that.statusId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(statusId);
    }
}

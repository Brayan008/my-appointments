package com.appointment.owner.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "roles_seq", sequenceName = "roles_sequence", allocationSize = 1)
@Table(name = "roles")
@Getter
@Setter
@ToString
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq")
    @Column(nullable = false)
    private Long roleId;

    private String name;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(roleId);
    }
}

package com.appointment.owner.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "customers_seq", sequenceName = "customers_sequence", allocationSize = 1)
@Table(name = "users")
@Getter
@Setter
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_seq")
    @Column(nullable = false)
    private Long userId;

    @Column(name = "status_id", nullable = false)
    private Long statusId;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private StatusEntity status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private RoleEntity role;

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
        UserEntity that = (UserEntity) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }
}

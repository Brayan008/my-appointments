package com.appointment.owner.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "memberships_seq", sequenceName = "memberships_sequence", allocationSize = 1)
@Table(name = "memberships")
@Setter
@Getter
@ToString
public class MembershipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memberships_seq")
    @Column(nullable = true)
    private Long membershipId;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MembershipEntity that = (MembershipEntity) o;
        return Objects.equals(membershipId, that.membershipId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(membershipId);
    }
}

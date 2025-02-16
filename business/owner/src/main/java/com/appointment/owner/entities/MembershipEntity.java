package com.appointment.owner.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

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
    @Column(nullable = false)
    private Long membershipId;

    @Column(nullable = false, length = 255)
    private String description;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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

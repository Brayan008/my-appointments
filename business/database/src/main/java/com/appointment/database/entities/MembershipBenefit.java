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
@SequenceGenerator(name = "membership_benefits_seq", sequenceName = "membership_benefits_sequence", allocationSize = 1)
@Table(name = "membership_benefits")
@Setter
@Getter
@ToString
public class MembershipBenefit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "membership_benefits_seq")
    @Column(name = "membership_benefit_id", nullable = false)
    private Long membershipBenefitId;

    @Column(nullable = false)
    private String benefit;

    @Column(nullable = false)
    private String description;

    @Column(name = "membership_id", nullable = false)
    private Long membershipId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "membership_id", insertable = false, updatable = false)
    private MembershipEntity membership;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MembershipBenefit that = (MembershipBenefit) o;
        return Objects.equals(membershipBenefitId, that.membershipBenefitId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(membershipBenefitId);
    }
}

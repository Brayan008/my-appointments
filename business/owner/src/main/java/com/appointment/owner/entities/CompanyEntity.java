package com.appointment.owner.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "companies_seq", sequenceName = "companies_sequence", allocationSize = 1)
@Table(name = "companies")
@Setter
@Getter
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companies_seq")
    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private String name;
    private String logo;
    @Column(nullable = false, length = 15)
    private String phoneNumber;
    private String instagramUrl;
    private String facebookUrl;

    @Column(name = "membership_id", nullable = false)
    private Long membershipId;

    @Column(name = "status_id", nullable = false)
    private Long statusId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "membership_id", insertable = false, updatable = false)
    private MembershipEntity membership;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private StatusEntity status;

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
        CompanyEntity that = (CompanyEntity) o;
        return Objects.equals(companyId, that.companyId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(companyId);
    }
}

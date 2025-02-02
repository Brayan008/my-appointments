package com.appointment.owner.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "companies_seq", sequenceName = "companies_sequence", allocationSize = 1)
@Table(name = "companies")
@Data
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companies_seq")
    @Column(nullable = true)
    private Long companyId;

    @Column(nullable = true)
    private String name;
    private String logo;
    @Column(nullable = true, length = 15)
    private String phoneNumber;
    private String instagramUrl;
    private String facebookUrl;

    @OneToMany(fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<MembershipEntity> membershipId = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false, unique = true)
    private StatusEntity statusId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

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

package com.appointment.database.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
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

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String logo;
    @Column(nullable = false, length = 15)
    private String phoneNumber;
    private String instagramUrl;
    private String facebookUrl;

    @Column(name = "membership_id", nullable = false)
    private Long membershipId;

    @Column(name = "status_id", nullable = false)
    private Long statusId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "membership_id", insertable = false, updatable = false)
    private MembershipEntity membership;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private StatusEntity status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company")
    private List<OwnerEntity> owners;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company")
    private List<StoreEntity> stores;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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

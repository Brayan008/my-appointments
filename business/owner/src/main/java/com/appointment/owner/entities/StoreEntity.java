package com.appointment.owner.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "stores_seq", sequenceName = "stores_sequence", allocationSize = 1)
@Table(name = "stores")
@Getter
@Setter
@ToString
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stores_seq")
    @Column(nullable = false)
    private Long storeId;

    private String name;

    @Column(nullable = false, length = 200)
    private String address;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, length = 400)
    private String coordinates;

    @Column(name = "status_id", nullable = false)
    private Long statusId;

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private StatusEntity status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity company;

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
        StoreEntity that = (StoreEntity) o;
        return Objects.equals(storeId, that.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(storeId);
    }
}

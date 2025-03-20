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
@SequenceGenerator(name = "owners_seq", sequenceName = "owners_sequence", allocationSize = 1)
@Table(name = "owners")
@Getter
@Setter
@ToString
public class OwnerEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owners_seq")
   @Column(nullable = false)
   private Long ownerId;

   @Column(name = "user_id", nullable = false)
   private Long userId;

   @Column(name = "company_id", nullable = false)
   private Long companyId;

   @Column(name = "owner_hierarchy", nullable = false)
   private String ownerHierarchy;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "user_id", insertable = false, updatable = false)
   private UserEntity user;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "company_id", insertable = false, updatable = false)
   private CompanyEntity company;

   @CreationTimestamp
   @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime createdAt;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      OwnerEntity that = (OwnerEntity) o;
      return Objects.equals(ownerId, that.ownerId);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(ownerId);
   }
}

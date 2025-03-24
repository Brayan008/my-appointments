package com.appointment.database.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
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

   @Column(nullable = false)
   private String name;

   @Column(nullable = false, length = 200)
   private String address;

   @Column(nullable = false, length = 300)
   private String description;

   @Column(nullable = false, length = 400)
   private String coordinates;

   @Column(name = "status_id", nullable = false)
   private Long statusId;

   @Column(name = "company_id", nullable = false)
   private Long companyId;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "status_id", insertable = false, updatable = false)
   private StatusEntity status;

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
      StoreEntity that = (StoreEntity) o;
      return Objects.equals(storeId, that.storeId);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(storeId);
   }
}

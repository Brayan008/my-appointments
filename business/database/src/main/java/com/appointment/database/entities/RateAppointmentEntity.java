package com.appointment.database.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.ws.rs.client.Client;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "rate_appointment_seq", sequenceName = "rate_appointment_sequence", allocationSize = 1)
@Table(name = "rate_appointment")
@Getter
@Setter
@ToString
public class RateAppointmentEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rate_date_seq")
   @Column(name = "rate_appointment_id", nullable = false)
   private Long rateAppointmentId;

   @Column(nullable = false, length = 1000)
   private String comment;

   @Check(constraints = "rate >= 0 and rate <=5")
   @Column(nullable = false)
   private Integer rate;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "client_appointment_id", updatable = false)
   private ClientAppointmentEntity clientAppointment;

   @CreationTimestamp
   @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime createdAt;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      RateAppointmentEntity that = (RateAppointmentEntity) o;
      return Objects.equals(rateAppointmentId, that.rateAppointmentId);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(rateAppointmentId);
   }
}

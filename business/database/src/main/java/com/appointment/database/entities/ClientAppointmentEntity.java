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
@SequenceGenerator(name = "client_appointments_seq", sequenceName = "client_appointments_sequence", allocationSize = 1)
@Table(name = "client_appointments")
@Getter
@Setter
@ToString
public class ClientAppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_appointments_seq")
    @Column(name = "client_appointment_id", nullable = false)
    private Long clientAppointmentId;

    @Column(name = "user_appointment", nullable = false)
    private LocalDateTime userAppointment;

    @Column(name = "status_appointment_id", nullable = false)
    private Long statusAppointmentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", insertable = false, updatable = false)
    private ServiceEntity serviceEntity;

    @Column(name = "total_paid", nullable = false)
    private Double totalPaid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private UserEntity clientEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_employee_id", insertable = false, updatable = false)
    private StoreEmployeeEntity storeEmployeeEntity;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAppointmentEntity that = (ClientAppointmentEntity) o;
        return Objects.equals(clientAppointmentId, that.clientAppointmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(clientAppointmentId);
    }
}

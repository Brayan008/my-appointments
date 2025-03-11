package com.appointment.database.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@SequenceGenerator(name = "config_employee_schedule_seq", sequenceName = "config_employee_schedule_sequence", allocationSize = 1)
@Table(name = "config_employee_schedule")
@Getter
@Setter
@ToString
public class ConfigEmployeeSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_favorite_stores_seq")
    @Column(name = "config_employee_schedule_id", nullable = false)
    private Long configEmployeeScheduleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_employees_id", insertable = false, updatable = false)
    private StoreEmployeeEntity storeEmployeeEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "default_status_date_id", insertable = false, updatable = false)
    private StatusAppointmentEntity defaultStatusAppointmentEntity;

    @Column(name = "day_of_week", nullable = false)
    private Integer dayOfWeek;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "start_time_break", nullable = false)
    private LocalTime startTimeBreak;

    @Column(name = "end_time_break", nullable = false)
    private LocalTime endTimeBreak;

    @Column(name = "interval_in_minutes", nullable = false)
    private Integer intervalInMinutes;

    @Column(name = "appointments_per_day", nullable = false)
    private Integer appointmentsPerDay;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

}

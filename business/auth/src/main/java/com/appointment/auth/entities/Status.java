package com.appointment.auth.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("status")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Status {
    @Id
    private Long id;
    private String name;
}

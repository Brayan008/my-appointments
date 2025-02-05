package com.appointment.auth.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @Column("user_id")
    private Long userId;
    private String email;
    private Long roleId;
    private Long statusId;
}

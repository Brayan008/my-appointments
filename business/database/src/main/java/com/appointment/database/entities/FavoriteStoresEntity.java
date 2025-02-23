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
@SequenceGenerator(name = "user_favorite_stores_seq", sequenceName = "user_favorite_stores_sequence", allocationSize = 1)
@Table(name = "user_favorite_stores")
@Getter
@Setter
@ToString
public class FavoriteStoresEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_favorite_stores_seq")
    @Column(name = "user_favorite_store_id", nullable = false)
    private Long favoriteStoreId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteStoresEntity that = (FavoriteStoresEntity) o;
        return Objects.equals(favoriteStoreId, that.favoriteStoreId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(favoriteStoreId);
    }
}

package com.appointment.database.repositories;

import com.appointment.database.entities.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
   List<StoreEntity> findByStatusId(Long statusId);

   @Query(value = "SELECT * FROM stores WHERE name ILIKE %:searchText% OR address ILIKE %:searchText% OR coordinates ILIKE %:searchText%",
      nativeQuery = true)
   List<StoreEntity> searchStores(@Param("searchText") String searchText);
}

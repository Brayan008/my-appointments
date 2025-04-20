package com.appointment.database.repositories;

import com.appointment.database.entities.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
   List<StoreEntity> findByStatusId(Long statusId);

   @Query(value = "SELECT * FROM stores WHERE name ILIKE %:searchText% OR address ILIKE %:searchText% OR description ILIKE %:searchText%",
      nativeQuery = true)
   List<StoreEntity> searchStores(@Param("searchText") String searchText);

   @Query(value = "SELECT * FROM stores " +
      "WHERE (name ILIKE %:searchText% " +
      "    OR address ILIKE %:searchText% " +
      "    OR description ILIKE %:searchText%) " +
      "AND earth_distance( " +
      "       ll_to_earth(CAST(:lat AS double precision), CAST(:lng AS double precision)), " +
      "       ll_to_earth(CAST(latitude AS double precision), CAST(longitude AS double precision))" +
      "    ) <= :radius",
      nativeQuery = true)
   List<StoreEntity> searchStoresByArea(@Param("searchText") String searchText,
                                        @Param("lat") double lat,
                                        @Param("lng") double lng,
                                        @Param("radius") int radius);
}

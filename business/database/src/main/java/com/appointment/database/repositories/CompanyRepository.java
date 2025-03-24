package com.appointment.database.repositories;

import com.appointment.database.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

   List<CompanyEntity> findByStatusId(Long statusId);

}

package com.appointment.owner.repositories;

import com.appointment.owner.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    List<CompanyEntity> findByStatusId(Long statusId);

}

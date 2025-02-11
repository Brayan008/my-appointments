package com.appointment.owner.services;

import com.appointment.owner.entities.CompanyEntity;

import java.util.List;

public interface CompanyService {
    List<CompanyEntity> getCompanies();

    CompanyEntity getCompanyById(Long companyId);

    CompanyEntity createCompany(CompanyEntity company);

    CompanyEntity updateCompany(CompanyEntity company, Long companyId);

    CompanyEntity disableById(Long companyId);

    CompanyEntity enableById(Long companyId);

    List<CompanyEntity> findByStatusId(Long statusId);
}

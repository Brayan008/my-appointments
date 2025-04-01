package com.appointment.database.services.impl;

import com.appointment.commons.enums.StatusEnum;
import com.appointment.commons.exceptions.ObjectNotFoundException;
import com.appointment.database.entities.CompanyEntity;
import com.appointment.database.repositories.CompanyRepository;
import com.appointment.database.services.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
   private final CompanyRepository companyRepository;

   @Override
   public List<CompanyEntity> getCompanies() {
      return companyRepository.findAll();
   }

   @Override
   public CompanyEntity getCompanyById(Long companyId) {
      return companyRepository.findById(companyId)
         .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
            "Company not found with id " + companyId, HttpStatus.NOT_FOUND));
   }

   @Override
   public CompanyEntity createCompany(CompanyEntity company) {
      return companyRepository.save(company);
   }

   @Override
   public CompanyEntity updateCompany(CompanyEntity company, Long companyId) {
      CompanyEntity currentCompany = this.getCompanyById(companyId);
      currentCompany.setName(company.getName());
      currentCompany.setLogo(company.getLogo());
      currentCompany.setPhoneNumber(company.getPhoneNumber());
      currentCompany.setInstagramUrl(company.getInstagramUrl());
      currentCompany.setFacebookUrl(company.getFacebookUrl());
      currentCompany.setMembershipId(company.getMembershipId());
      currentCompany.setStatusId(company.getStatusId());
      return this.createCompany(currentCompany);
   }

   @Override
   public CompanyEntity disableById(Long companyId) {
      CompanyEntity currentCompany = this.getCompanyById(companyId);
      currentCompany.setStatusId(StatusEnum.DISABLED.getCode());
      return this.createCompany(currentCompany);
   }

   @Override
   public CompanyEntity enableById(Long companyId) {
      CompanyEntity currentCompany = this.getCompanyById(companyId);
      currentCompany.setStatusId(StatusEnum.ENABLED.getCode());
      return this.createCompany(currentCompany);
   }

   @Override
   public List<CompanyEntity> findByStatusId(Long statusId) {
      return companyRepository.findByStatusId(statusId);
   }
}

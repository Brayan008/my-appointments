package com.appointment.owner.business.impl;

import com.appointment.commons.enums.Status;
import com.appointment.owner.business.CompanyBusiness;
import com.appointment.owner.commons.mappers.CompanyRequestMapper;
import com.appointment.owner.commons.mappers.CompanyResponseMapper;
import com.appointment.owner.dtos.request.CompanyRequest;
import com.appointment.owner.dtos.response.CompanyResponse;
import com.appointment.owner.entities.CompanyEntity;
import com.appointment.owner.services.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyBusinessImpl implements CompanyBusiness {

    private final CompanyService companyService;

    @Autowired
    private CompanyResponseMapper companyResponseMapper;

    @Autowired
    private CompanyRequestMapper companyRequestMapper;

    @Override
    public List<CompanyResponse> getCompanies() {
        return companyResponseMapper.companyEntityListToCompanyResponseList(
            this.companyService.getCompanies()
        );
    }

    @Override
    public CompanyResponse getCompanyById(Long companyId) {
        return companyResponseMapper.companyEntityToCompanyResponse(
            this.companyService.getCompanyById(companyId)
        );
    }

    @Override
    public CompanyResponse createCompany(CompanyRequest companyRequest) {
        CompanyEntity companyEntity = companyRequestMapper.companyRequestToCompanyEntity(companyRequest);
        CompanyEntity newCompany = this.companyService.createCompany(companyEntity);

        return this.companyResponseMapper.companyEntityToCompanyResponse(newCompany);
    }

    @Override
    public CompanyResponse updateCompany(CompanyRequest companyRequest, Long companyId) {
        CompanyEntity companyEntity = companyRequestMapper.companyRequestToCompanyEntity(companyRequest);
        CompanyEntity companyUpdated = this.companyService.updateCompany(companyEntity, companyId);
        return this.companyResponseMapper.companyEntityToCompanyResponse(companyUpdated);
    }

    @Override
    public CompanyResponse disableById(Long companyId) {
        CompanyEntity company = companyService.disableById(companyId);
        return this.companyResponseMapper.companyEntityToCompanyResponse(company);
    }

    @Override
    public CompanyResponse enableById(Long companyId) {
        CompanyEntity company = companyService.enableById(companyId);
        return this.companyResponseMapper.companyEntityToCompanyResponse(company);
    }

    @Override
    public List<CompanyResponse> getEnabledCompany() {
        List<CompanyEntity> companies = this.companyService.findByStatusId(Status.ENABLED.getCode());
        return this.companyResponseMapper.companyEntityListToCompanyResponseList(companies);
    }

    @Override
    public List<CompanyResponse> getDisabledCompany() {
        List<CompanyEntity> companies = this.companyService.findByStatusId(Status.DISABLED.getCode());
        return this.companyResponseMapper.companyEntityListToCompanyResponseList(companies);
    }
}

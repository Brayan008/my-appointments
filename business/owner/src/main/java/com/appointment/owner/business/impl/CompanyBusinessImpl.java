package com.appointment.owner.business.impl;

import com.appointment.commons.dtos.request.CompanyRequest;
import com.appointment.commons.dtos.response.CompanyResponse;
import com.appointment.commons.enums.Status;
import com.appointment.owner.business.CompanyBusiness;
import com.appointment.owner.services.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyBusinessImpl implements CompanyBusiness {

    private final CompanyService companyService;

    @Override
    public List<CompanyResponse> getCompanies() {
        return this.companyService.getCompanies();
    }

    @Override
    public CompanyResponse getCompanyById(Long companyId) {
        return this.companyService.getCompanyById(companyId);
    }

    @Override
    public CompanyResponse createCompany(CompanyRequest companyRequest) {
        return this.companyService.createCompany(companyRequest);
    }

    @Override
    public CompanyResponse updateCompany(CompanyRequest companyRequest, Long companyId) {
        return this.companyService.updateCompany(companyRequest, companyId);
    }

    @Override
    public CompanyResponse disableById(Long companyId) {
        return this.companyService.disableById(companyId);
    }

    @Override
    public CompanyResponse enableById(Long companyId) {
        return this.companyService.enableById(companyId);
    }

    @Override
    public List<CompanyResponse> getEnabledCompany() {
        return this.companyService.findByStatusId(Status.ENABLED.getCode());
    }

    @Override
    public List<CompanyResponse> getDisabledCompany() {
        return this.companyService.findByStatusId(Status.DISABLED.getCode());
    }
}

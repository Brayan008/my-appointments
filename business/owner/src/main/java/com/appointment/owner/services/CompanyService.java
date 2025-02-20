package com.appointment.owner.services;

import com.appointment.commons.dtos.request.CompanyRequest;
import com.appointment.commons.dtos.response.CompanyResponse;

import java.util.List;

public interface CompanyService {
    List<CompanyResponse> getCompanies();

    CompanyResponse getCompanyById(Long companyId);

    CompanyResponse createCompany(CompanyRequest company);

    CompanyResponse updateCompany(CompanyRequest company, Long companyId);

    CompanyResponse disableById(Long companyId);

    CompanyResponse enableById(Long companyId);

    List<CompanyResponse> findByStatusId(Long statusId);
}

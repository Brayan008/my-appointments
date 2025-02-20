package com.appointment.owner.business;


import com.appointment.commons.dtos.request.CompanyRequest;
import com.appointment.commons.dtos.response.CompanyResponse;

import java.util.List;

public interface CompanyBusiness {
    List<CompanyResponse> getCompanies();

    CompanyResponse getCompanyById(Long companyId);

    CompanyResponse createCompany(CompanyRequest company);

    CompanyResponse updateCompany(CompanyRequest company, Long companyId);

    CompanyResponse disableById(Long companyId);

    CompanyResponse enableById(Long companyId);

    List<CompanyResponse> getEnabledCompany();

    List<CompanyResponse> getDisabledCompany();
}

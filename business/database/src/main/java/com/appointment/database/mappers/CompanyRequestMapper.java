package com.appointment.database.mappers;

import com.appointment.commons.dtos.request.CompanyRequest;
import com.appointment.database.entities.CompanyEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyRequestMapper {

    CompanyEntity companyRequestToCompanyEntity(CompanyRequest companyRequest);

    List<CompanyEntity> companyRequestsToCompanyEntities(List<CompanyRequest> companyRequests);

    CompanyRequest companyEntityToCompanyRequest(CompanyEntity companyEntity);

}

package com.appointment.owner.commons.mappers;

import com.appointment.owner.dtos.request.CompanyRequest;
import com.appointment.owner.entities.CompanyEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyRequestMapper {

    CompanyEntity companyRequestToCompanyEntity(CompanyRequest companyRequest);

    List<CompanyEntity> companyRequestsToCompanyEntities(List<CompanyRequest> companyRequests);

    CompanyRequest companyEntityToCompanyRequest(CompanyEntity companyEntity);

}

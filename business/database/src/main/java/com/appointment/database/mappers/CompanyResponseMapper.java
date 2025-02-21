package com.appointment.database.mappers;

import com.appointment.commons.dtos.response.CompanyResponse;
import com.appointment.database.entities.CompanyEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyResponseMapper {

    CompanyResponse companyEntityToCompanyResponse(CompanyEntity companyEntity);

    List<CompanyResponse> companyEntityListToCompanyResponseList(List<CompanyEntity> companyEntities);

    CompanyEntity companyResponseToCompanyEntity(CompanyResponse companyRequest);
}

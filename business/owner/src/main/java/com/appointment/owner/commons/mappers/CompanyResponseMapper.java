package com.appointment.owner.commons.mappers;

import com.appointment.owner.dtos.response.CompanyResponse;
import com.appointment.owner.entities.CompanyEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyResponseMapper {

    CompanyResponse companyEntityToCompanyResponse(CompanyEntity companyEntity);

    List<CompanyResponse> companyEntityListToCompanyResponseList(List<CompanyEntity> companyEntities);

    CompanyEntity companyResponseToCompanyEntity(CompanyResponse companyRequest);
}

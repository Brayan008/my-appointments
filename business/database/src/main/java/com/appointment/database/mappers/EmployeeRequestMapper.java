package com.appointment.database.mappers;

import com.appointment.commons.dtos.request.EmployeeRequest;
import com.appointment.database.entities.EmployeeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeRequestMapper {

    EmployeeEntity employeeRequestToEmployeeEntity(EmployeeRequest employeeRequest);

    EmployeeRequest employeeEntityToEmployeeRequest(EmployeeEntity employeeEntity);

    List<EmployeeEntity> employeeRequestsToEmployeeEntities(List<EmployeeRequest> employeeRequests);
}

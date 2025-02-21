package com.appointment.database.mappers;

import com.appointment.commons.dtos.response.EmployeeResponse;
import com.appointment.commons.dtos.response.UserResponse;
import com.appointment.database.entities.EmployeeEntity;
import com.appointment.database.entities.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeResponseMapper {

    EmployeeResponse employeeEntityToEmployeeResponse(EmployeeEntity employeeEntity);

    EmployeeEntity employeeResponseToEmployeeEntity(EmployeeResponse employeeResponse);

    List<EmployeeResponse> employeeEntitiesToEmployeeResponses(List<EmployeeEntity> employees);

    List<UserResponse> userEntitiesToUserResponses(List<UserEntity> employees);
}

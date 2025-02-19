package com.appointment.owner.commons.mappers;

import com.appointment.owner.dtos.response.EmployeeResponse;
import com.appointment.owner.dtos.response.UserResponse;
import com.appointment.owner.entities.EmployeeEntity;
import com.appointment.owner.entities.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeResponseMapper {

    EmployeeResponse employeeEntityToEmployeeResponse(EmployeeEntity employeeEntity);

    EmployeeEntity employeeResponseToEmployeeEntity(EmployeeResponse employeeResponse);

    List<EmployeeResponse> employeeEntitiesToEmployeeResponses(List<EmployeeEntity> employees);

    List<UserResponse> userEntitiesToUserResponses(List<UserEntity> employees);
}

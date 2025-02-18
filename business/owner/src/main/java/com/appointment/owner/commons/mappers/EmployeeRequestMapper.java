package com.appointment.owner.commons.mappers;

import com.appointment.owner.dtos.request.EmployeeRequest;
import com.appointment.owner.dtos.request.MembershipRequest;
import com.appointment.owner.entities.EmployeeEntity;
import com.appointment.owner.entities.MembershipEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeRequestMapper {

    EmployeeEntity employeeRequestToEmployeeEntity(EmployeeRequest employeeRequest);

    EmployeeRequest employeeEntityToEmployeeRequest(EmployeeEntity employeeEntity);

    List<EmployeeEntity> employeeRequestsToEmployeeEntities(List<EmployeeRequest> employeeRequests);
}

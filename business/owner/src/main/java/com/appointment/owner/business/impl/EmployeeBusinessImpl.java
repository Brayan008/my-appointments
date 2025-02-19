package com.appointment.owner.business.impl;

import com.appointment.commons.enums.Status;
import com.appointment.owner.business.EmployeeBusiness;
import com.appointment.owner.commons.mappers.EmployeeRequestMapper;
import com.appointment.owner.commons.mappers.EmployeeResponseMapper;
import com.appointment.owner.dtos.request.EmployeeRequest;
import com.appointment.owner.dtos.response.EmployeeResponse;
import com.appointment.owner.dtos.response.UserResponse;
import com.appointment.owner.entities.EmployeeEntity;
import com.appointment.owner.entities.UserEntity;
import com.appointment.owner.services.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeBusinessImpl implements EmployeeBusiness {
    private final EmployeeService employeeService;

    @Autowired
    private EmployeeResponseMapper employeeResponseMapper;

    @Autowired
    private EmployeeRequestMapper employeeRequestMapper;

    @Override
    public List<EmployeeResponse> getEmployees() {
        return employeeResponseMapper.employeeEntitiesToEmployeeResponses(
            this.employeeService.getEmployees()
        );
    }

    @Override
    public EmployeeResponse getEmployeeById(Long employeeId) {
        return employeeResponseMapper.employeeEntityToEmployeeResponse(
            this.employeeService.getEmployeeById(employeeId)
        );
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employee) {
        EmployeeEntity currentEmployee = employeeRequestMapper.employeeRequestToEmployeeEntity(employee);
        EmployeeEntity newEmployee = this.employeeService.createEmployee(currentEmployee);

        return this.employeeResponseMapper.employeeEntityToEmployeeResponse(newEmployee);
    }

    @Override
    public EmployeeResponse updateEmployee(EmployeeRequest employee, Long employeeId) {
        EmployeeEntity currentEmployee = employeeRequestMapper.employeeRequestToEmployeeEntity(employee);
        EmployeeEntity employeeUpdated = this.employeeService.updateEmployee(currentEmployee, employeeId);

        return this.employeeResponseMapper.employeeEntityToEmployeeResponse(employeeUpdated);
    }

    @Override
    public EmployeeResponse disableById(Long employeeId) {
        EmployeeEntity employee = employeeService.disableById(employeeId);
        return this.employeeResponseMapper.employeeEntityToEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse enableById(Long employeeId) {
        EmployeeEntity employee = employeeService.enableById(employeeId);
        return this.employeeResponseMapper.employeeEntityToEmployeeResponse(employee);
    }

    @Override
    public List<EmployeeResponse> getEnabledEmployee() {
        List<EmployeeEntity> employees = this.employeeService.findByStatusId(Status.ENABLED.getCode());
        return this.employeeResponseMapper.employeeEntitiesToEmployeeResponses(employees);
    }

    @Override
    public List<EmployeeResponse> getDisabledEmployee() {
        List<EmployeeEntity> employees = this.employeeService.findByStatusId(Status.DISABLED.getCode());
        return this.employeeResponseMapper.employeeEntitiesToEmployeeResponses(employees);
    }

    @Override
    public List<UserResponse> getEmployeesAssociated(Long storeId) {
        List<UserEntity> users = employeeService.getEmployeesAssociated(storeId);
        return employeeResponseMapper.userEntitiesToUserResponses(users);
    }
}

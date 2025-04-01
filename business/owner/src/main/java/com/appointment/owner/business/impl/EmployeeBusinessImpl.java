package com.appointment.owner.business.impl;

import com.appointment.commons.dtos.request.EmployeeRequest;
import com.appointment.commons.dtos.response.EmployeeResponse;
import com.appointment.commons.dtos.response.UserResponse;
import com.appointment.commons.enums.StatusEnum;
import com.appointment.owner.business.EmployeeBusiness;
import com.appointment.owner.services.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeBusinessImpl implements EmployeeBusiness {
    private final EmployeeService employeeService;

    @Override
    public List<EmployeeResponse> getEmployees() {
        return this.employeeService.getEmployees();
    }

    @Override
    public EmployeeResponse getEmployeeById(Long employeeId) {
        return this.employeeService.getEmployeeById(employeeId);
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employee) {
        return this.employeeService.createEmployee(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(EmployeeRequest employee, Long employeeId) {
        return this.employeeService.updateEmployee(employee, employeeId);
    }

    @Override
    public EmployeeResponse disableById(Long employeeId) {
        return this.employeeService.disableById(employeeId);
    }

    @Override
    public EmployeeResponse enableById(Long employeeId) {
        return this.employeeService.enableById(employeeId);
    }

    @Override
    public List<EmployeeResponse> getEnabledEmployee() {
        return this.employeeService.findByStatusId(StatusEnum.ENABLED.getCode());
    }

    @Override
    public List<EmployeeResponse> getDisabledEmployee() {
        return this.employeeService.findByStatusId(StatusEnum.DISABLED.getCode());
    }

    @Override
    public List<UserResponse> getEmployeesAssociated(Long storeId) {
        return this.employeeService.getEmployeesAssociated(storeId);
    }
}

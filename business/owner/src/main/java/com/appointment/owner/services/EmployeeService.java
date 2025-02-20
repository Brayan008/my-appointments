package com.appointment.owner.services;

import com.appointment.commons.dtos.request.EmployeeRequest;
import com.appointment.commons.dtos.response.EmployeeResponse;
import com.appointment.commons.dtos.response.UserResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> getEmployees();

    EmployeeResponse getEmployeeById(Long employeeId);

    EmployeeResponse createEmployee(EmployeeRequest employee);

    EmployeeResponse updateEmployee(EmployeeRequest currentEmployee, Long employeeId);

    EmployeeResponse disableById(Long employeeId);

    EmployeeResponse enableById(Long employeeId);

    List<EmployeeResponse> findByStatusId(Long statusId);

    List<UserResponse> getEmployeesAssociated(Long storeId);
}

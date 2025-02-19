package com.appointment.owner.business;

import com.appointment.owner.dtos.request.EmployeeRequest;
import com.appointment.owner.dtos.response.EmployeeResponse;
import com.appointment.owner.dtos.response.UserResponse;

import java.util.List;

public interface EmployeeBusiness {
    List<EmployeeResponse> getEmployees();

    EmployeeResponse getEmployeeById(Long employeeId);

    EmployeeResponse createEmployee(EmployeeRequest employee);

    EmployeeResponse updateEmployee(EmployeeRequest employee, Long employeeId);

    EmployeeResponse disableById(Long employeeId);

    EmployeeResponse enableById(Long employeeId);

    List<EmployeeResponse> getEnabledEmployee();

    List<EmployeeResponse> getDisabledEmployee();

    List<UserResponse> getEmployeesAssociated(Long storeId);
}

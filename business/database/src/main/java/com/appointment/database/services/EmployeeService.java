package com.appointment.database.services;

import com.appointment.database.entities.EmployeeEntity;
import com.appointment.database.entities.UserEntity;

import java.util.List;

public interface EmployeeService {
    List<EmployeeEntity> getEmployees();

    EmployeeEntity getEmployeeById(Long employeeId);

    EmployeeEntity createEmployee(EmployeeEntity employee);

    EmployeeEntity updateEmployee(EmployeeEntity currentEmployee, Long employeeId);

    EmployeeEntity disableById(Long employeeId);

    EmployeeEntity enableById(Long employeeId);

    List<EmployeeEntity> findByStatusId(Long statusId);

    List<UserEntity> getEmployeesAssociated(Long storeId);
}

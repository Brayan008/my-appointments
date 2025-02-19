package com.appointment.owner.services;

import com.appointment.owner.entities.EmployeeEntity;
import com.appointment.owner.entities.UserEntity;

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

package com.appointment.owner.services.impl;

import com.appointment.commons.enums.Status;
import com.appointment.commons.exceptions.ObjectNotFoundException;
import com.appointment.owner.entities.EmployeeEntity;
import com.appointment.owner.entities.UserEntity;
import com.appointment.owner.repositories.EmployeeRepository;
import com.appointment.owner.services.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeEntity> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeEntity getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
                "employee not found with id " + employeeId, HttpStatus.NOT_FOUND));
    }

    @Override
    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeEntity updateEmployee(EmployeeEntity currentEmployee, Long employeeId) {
        EmployeeEntity employeeUpdated = this.getEmployeeById(employeeId);
        employeeUpdated.setUserId(currentEmployee.getUserId());
        employeeUpdated.setStoreId(currentEmployee.getStoreId());
        employeeUpdated.setStatusId(currentEmployee.getStatusId());
        return this.createEmployee(employeeUpdated);
    }

    @Override
    public EmployeeEntity disableById(Long employeeId) {
        EmployeeEntity currentEmployee = this.getEmployeeById(employeeId);
        currentEmployee.setStatusId(Status.DISABLED.getCode());
        return this.createEmployee(currentEmployee);
    }

    @Override
    public EmployeeEntity enableById(Long employeeId) {
        EmployeeEntity currentEmployee = this.getEmployeeById(employeeId);
        currentEmployee.setStatusId(Status.ENABLED.getCode());
        return this.createEmployee(currentEmployee);
    }

    @Override
    public List<EmployeeEntity> findByStatusId(Long statusId) {
        return employeeRepository.findByStatusId(statusId);
    }

    @Override
    public List<UserEntity> getEmployeesAssociated(Long storeId) {
        return employeeRepository.findEmployeesByStoreId(storeId);
    }
}

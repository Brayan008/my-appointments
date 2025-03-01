package com.appointment.database.services.impl;

import com.appointment.commons.enums.Status;
import com.appointment.commons.exceptions.ObjectNotFoundException;
import com.appointment.database.entities.StoreEmployeeEntity;
import com.appointment.database.entities.UserEntity;
import com.appointment.database.repositories.StoreEmployeeRepository;
import com.appointment.database.services.StoreEmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StoreEmployeeServiceImpl implements StoreEmployeeService {

    private final StoreEmployeeRepository storeEmployeeRepository;

    @Override
    public List<StoreEmployeeEntity> getEmployees() {
        return storeEmployeeRepository.findAll();
    }

    @Override
    public StoreEmployeeEntity getEmployeeById(Long employeeId) {
        return storeEmployeeRepository.findById(employeeId)
            .orElseThrow(() -> new ObjectNotFoundException(HttpStatus.NOT_FOUND.value(),
                "employee not found with id " + employeeId, HttpStatus.NOT_FOUND));
    }

    @Override
    public StoreEmployeeEntity createEmployee(StoreEmployeeEntity employee) {
        return storeEmployeeRepository.save(employee);
    }

    @Override
    public StoreEmployeeEntity updateEmployee(StoreEmployeeEntity currentEmployee, Long employeeId) {
        StoreEmployeeEntity employeeUpdated = this.getEmployeeById(employeeId);
        employeeUpdated.setUserId(currentEmployee.getUserId());
        employeeUpdated.setStoreId(currentEmployee.getStoreId());
        employeeUpdated.setStatusId(currentEmployee.getStatusId());
        return this.createEmployee(employeeUpdated);
    }

    @Override
    public StoreEmployeeEntity disableById(Long employeeId) {
        StoreEmployeeEntity currentEmployee = this.getEmployeeById(employeeId);
        currentEmployee.setStatusId(Status.DISABLED.getCode());
        return this.createEmployee(currentEmployee);
    }

    @Override
    public StoreEmployeeEntity enableById(Long employeeId) {
        StoreEmployeeEntity currentEmployee = this.getEmployeeById(employeeId);
        currentEmployee.setStatusId(Status.ENABLED.getCode());
        return this.createEmployee(currentEmployee);
    }

    @Override
    public List<StoreEmployeeEntity> findByStatusId(Long statusId) {
        return storeEmployeeRepository.findByStatusId(statusId);
    }

    @Override
    public List<UserEntity> getEmployeesAssociated(Long storeId) {
        return storeEmployeeRepository.findEmployeesByStoreId(storeId);
    }
}

package com.appointment.owner.services.impl;

import com.appointment.commons.dtos.StandardizedApiExceptionResponse;
import com.appointment.commons.dtos.request.EmployeeRequest;
import com.appointment.commons.dtos.response.EmployeeResponse;
import com.appointment.commons.dtos.response.UserResponse;
import com.appointment.commons.exceptions.BusinessException;
import com.appointment.owner.commons.mappers.ExceptionMapper;
import com.appointment.owner.services.EmployeeService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
   private final RestClient restClient;
   private final ExceptionMapper exceptionMapper;

   @Override
   public List<EmployeeResponse> getEmployees() {
      return restClient
         .get()
         .uri("/employees")
         .retrieve()
         .onStatus(HttpStatusCode::isError, (req, res) -> {
            log.error("Error: {}", res);
            StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
            throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
         })
         .body(new ParameterizedTypeReference<>() {});
   }

   @Override
   public EmployeeResponse getEmployeeById(Long employeeId) {
      return restClient
         .get()
         .uri("/employees/"+employeeId)
         .retrieve()
         .onStatus(HttpStatusCode::isError, (req, res) -> {
            log.error("Error: {}", res);
            StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
            throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
         })
         .body(EmployeeResponse.class);
   }

   @Override
   public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
      return restClient
         .post()
         .uri("/employees")
         .body(employeeRequest)
         .retrieve()
         .onStatus(HttpStatusCode::isError, (req, res) -> {
            log.error("Error: {}", res);
            StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
            throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
         })
         .body(EmployeeResponse.class);
   }

   @Override
   public EmployeeResponse updateEmployee(EmployeeRequest employeeRequest, Long employeeId) {
      return restClient
         .put()
         .uri("/employees/"+employeeId)
         .body(employeeRequest)
         .retrieve()
         .onStatus(HttpStatusCode::isError, (req, res) -> {
            log.error("Error: {}", res);
            StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
            throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
         })
         .body(EmployeeResponse.class);
   }

   @Override
   public EmployeeResponse disableById(Long employeeId) {
      return restClient
         .put()
         .uri("/employees/"+employeeId+"/disabled")
         .retrieve()
         .onStatus(HttpStatusCode::isError, (req, res) -> {
            log.error("Error: {}", res);
            StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
            throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
         })
         .body(EmployeeResponse.class);
   }

   @Override
   public EmployeeResponse enableById(Long employeeId) {
      return restClient
         .put()
         .uri("/employees/"+employeeId+"/enabled")
         .retrieve()
         .onStatus(HttpStatusCode::isError, (req, res) -> {
            log.error("Error: {}", res);
            StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
            throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
         })
         .body(EmployeeResponse.class);
   }

   @Override
   public List<EmployeeResponse> findByStatusId(Long statusId) {
      return restClient
         .get()
         .uri("/employees/status/"+statusId)
         .retrieve()
         .onStatus(HttpStatusCode::isError, (req, res) -> {
            log.error("Error: {}", res);
            StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
            throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
         })
         .body(new ParameterizedTypeReference<>() {});
   }

   @Override
   public List<UserResponse> getEmployeesAssociated(Long storeId) {
      return restClient
         .get()
         .uri("/store/"+storeId+"/employees")
         .retrieve()
         .onStatus(HttpStatusCode::isError, (req, res) -> {
            log.error("Error: {}", res);
            StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
            throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
         })
         .body(new ParameterizedTypeReference<>() {});
   }
}

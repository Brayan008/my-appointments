package com.appointment.owner.services.impl;

import com.appointment.commons.dtos.StandardizedApiExceptionResponse;
import com.appointment.commons.dtos.request.CompanyRequest;
import com.appointment.commons.dtos.response.CompanyResponse;
import com.appointment.commons.exceptions.BusinessException;
import com.appointment.owner.commons.mappers.ExceptionMapper;
import com.appointment.owner.services.CompanyService;
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
public class CompanyServiceImpl implements CompanyService {

    private RestClient restClient;
    private final ExceptionMapper exceptionMapper;

    @Value("${microservice.endpoint.database}")
    private String domain;

    @PostConstruct
    public void init() {
        this.restClient = RestClient.builder()
            .baseUrl(domain)
            .build();
    }

    @Override
    public List<CompanyResponse> getCompanies() {
        return restClient.get()
            .uri("/company/companies")
            .retrieve()
            .onStatus(HttpStatusCode::isError, (req, res) -> {
                log.error("Error: {}", res);
                StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
                throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
            })
            .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public CompanyResponse createCompany(CompanyRequest companyRequest) {
        return restClient
            .post()
            .uri("/company/companies")
            .body(companyRequest)
            .retrieve()
            .onStatus(HttpStatusCode::isError, (req, res) -> {
                log.error("Error: {}", res);
                StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
                throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
            })
            .body(CompanyResponse.class);
    }

    @Override
    public CompanyResponse getCompanyById(Long companyId) {
        return restClient
            .get()
            .uri("/company/"+companyId)
            .retrieve()
            .onStatus(HttpStatusCode::isError, (req, res) -> {
                log.error("Error: {}", res);
                StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
                throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
            })
            .body(CompanyResponse.class);
    }

    @Override
    public CompanyResponse updateCompany(CompanyRequest company, Long companyId) {
        return restClient
            .put()
            .uri("/company/"+companyId)
            .body(company)
            .retrieve()
            .onStatus(HttpStatusCode::isError, (req, res) -> {
                log.error("Error: {}", res);
                StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
                throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
            })
            .body(CompanyResponse.class);
    }

    @Override
    public CompanyResponse disableById(Long companyId) {
        return restClient
            .put()
            .uri("/company/"+companyId+"/enabled")
            .retrieve()
            .onStatus(HttpStatusCode::isError, (req, res) -> {
                log.error("Error: {}", res);
                StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
                throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
            })
            .body(CompanyResponse.class);
    }

    @Override
    public CompanyResponse enableById(Long companyId) {
        return restClient
            .put()
            .uri("/company/"+companyId+"/disabled")
            .retrieve()
            .onStatus(HttpStatusCode::isError, (req, res) -> {
                log.error("Error: {}", res);
                StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
                throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
            })
            .body(CompanyResponse.class);
    }

    @Override
    public List<CompanyResponse> findByStatusId(Long statusId) {
        return null;
    }
}

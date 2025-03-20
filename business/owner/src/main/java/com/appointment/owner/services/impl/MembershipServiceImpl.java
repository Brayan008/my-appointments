package com.appointment.owner.services.impl;

import com.appointment.commons.dtos.StandardizedApiExceptionResponse;
import com.appointment.commons.dtos.request.MembershipRequest;
import com.appointment.commons.dtos.response.MembershipResponse;
import com.appointment.commons.exceptions.BusinessException;
import com.appointment.owner.commons.mappers.ExceptionMapper;
import com.appointment.owner.services.MembershipService;
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
public class MembershipServiceImpl implements MembershipService {
   private final RestClient restClient;
   private final ExceptionMapper exceptionMapper;

   @Override
   public List<MembershipResponse> getMemberships() {
      return restClient
         .get()
         .uri("/memberships")
         .retrieve()
         .onStatus(HttpStatusCode::isError, (req, res) -> {
            log.error("Error: {}", res);
            StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
            throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
         })
         .body(new ParameterizedTypeReference<>() {});
   }

   @Override
   public MembershipResponse getMembershipById(Long membershipId) {
      return restClient
         .get()
         .uri("/memberships/"+membershipId)
         .retrieve()
         .onStatus(HttpStatusCode::isError, (req, res) -> {
            log.error("Error: {}", res);
            StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
            throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
         })
         .body(MembershipResponse.class);
   }

   @Override
   public MembershipResponse createMembership(MembershipRequest membershipRequest) {
      return restClient
         .post()
         .uri("/memberships")
         .body(membershipRequest)
         .retrieve()
         .onStatus(HttpStatusCode::isError, (req, res) -> {
            log.error("Error: {}", res);
            StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
            throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
         })
         .body(MembershipResponse.class);
   }

   @Override
   public MembershipResponse updateMembership(MembershipRequest membership, Long membershipId) {
      return restClient
         .put()
         .uri("/memberships/"+membershipId)
         .body(membership)
         .retrieve()
         .onStatus(HttpStatusCode::isError, (req, res) -> {
            log.error("Error: {}", res);
            StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
            throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
         })
         .body(MembershipResponse.class);
   }

   @Override
   public MembershipResponse disableById(Long membershipId) {
      return restClient
         .put()
         .uri("/memberships/"+membershipId+"/disabled")
         .retrieve()
         .onStatus(HttpStatusCode::isError, (req, res) -> {
            log.error("Error: {}", res);
            StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
            throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
         })
         .body(MembershipResponse.class);
   }

   @Override
   public MembershipResponse enableById(Long membershipId) {
      return restClient
         .put()
         .uri("/memberships/"+membershipId+"/enabled")
         .retrieve()
         .onStatus(HttpStatusCode::isError, (req, res) -> {
            log.error("Error: {}", res);
            StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
            throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
         })
         .body(MembershipResponse.class);
   }

   @Override
   public List<MembershipResponse> findByStatusId(Long statusId) {
      return restClient
         .get()
         .uri("/memberships/status/"+statusId)
         .retrieve()
         .onStatus(HttpStatusCode::isError, (req, res) -> {
            log.error("Error: {}", res);
            StandardizedApiExceptionResponse error = exceptionMapper.apiExceptionMapper(res.getBody());
            throw new BusinessException(error.getCode(), error.getTitle(), error.getDetail(), HttpStatus.valueOf(res.getStatusCode().value()));
         })
         .body(new ParameterizedTypeReference<>() {});
   }
}

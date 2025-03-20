package com.appointment.owner.services.impl;

import com.appointment.owner.commons.mappers.ExceptionMapper;
import com.appointment.owner.services.OwnerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {
   private final RestClient restClient;
   private final ExceptionMapper exceptionMapper;
}

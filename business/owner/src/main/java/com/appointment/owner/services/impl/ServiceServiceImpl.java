package com.appointment.owner.services.impl;

import com.appointment.owner.commons.mappers.ExceptionMapper;
import com.appointment.owner.services.ServiceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceServiceImpl implements ServiceService {
   private final RestClient restClient;
   private final ExceptionMapper exceptionMapper;
}

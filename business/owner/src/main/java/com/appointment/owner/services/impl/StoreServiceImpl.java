package com.appointment.owner.services.impl;

import com.appointment.owner.commons.mappers.ExceptionMapper;
import com.appointment.owner.services.StoreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {
   private final RestClient restClient;
   private final ExceptionMapper exceptionMapper;
}

package com.appointment.owner.services.impl;

import com.appointment.owner.repositories.StoreRepository;
import com.appointment.owner.services.StoreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
}

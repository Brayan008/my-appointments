package com.appointment.database.services.impl;

import com.appointment.database.repositories.StoreRepository;
import com.appointment.database.services.StoreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
}

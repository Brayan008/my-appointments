package com.appointment.auth.service;

import com.appointment.auth.dto.UserDTOReq;
import com.appointment.auth.dto.UserDTORes;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserDTORes> validateLoginUser(UserDTOReq userDTOReq);
}

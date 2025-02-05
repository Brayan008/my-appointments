package com.appointment.auth.business;

import com.appointment.auth.dto.UserInfoDTORes;
import reactor.core.publisher.Mono;

public interface UserBusiness {

    Mono<UserInfoDTORes> getUserInfo(String accessToken, String email);

}

package com.appointment.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("id_token")
    private String apiToken;

}

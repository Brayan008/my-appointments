package com.appointment.commons.utils;

import com.appointment.commons.dtos.JwtStructure;

public interface JwtUtils {
   JwtStructure getJwtStructure(String token);
}

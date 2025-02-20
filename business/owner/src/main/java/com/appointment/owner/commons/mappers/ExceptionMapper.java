package com.appointment.owner.commons.mappers;

import com.appointment.commons.dtos.StandardizedApiExceptionResponse;
import org.mapstruct.Mapper;

import java.io.InputStream;

@Mapper(componentModel = "spring")
public interface ExceptionMapper {
    StandardizedApiExceptionResponse apiExceptionMapper(InputStream body);


}

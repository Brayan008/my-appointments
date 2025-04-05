package com.appointment.commons.utils.impl;

import com.appointment.commons.dtos.JwtStructure;
import com.appointment.commons.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class JwtUtilsImpl implements JwtUtils {
   @Override
   public JwtStructure getJwtStructure(String token) {
      try {
         Base64.Decoder decoder = Base64.getUrlDecoder();
         String[] chunks = token.split("\\.");
         String payload = new String(decoder.decode(chunks[1]));
         System.out.println(payload);
         ObjectMapper objectMapper = new ObjectMapper();
         return objectMapper.readValue(payload, JwtStructure.class);
      } catch (Exception e) {
         throw new RuntimeException("No se pudo parsear el JWT", e);
      }
   }
}

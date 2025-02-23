package com.easework.simuapi.simu_api.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

    HttpStatus httpStatus;
    String stringMsg;
    JsonNode jsonData;
    
}

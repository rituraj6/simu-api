package com.easework.simuapi.simu_api.dto;


import com.easework.simuapi.simu_api.enums.RequestBodyType;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiDetailsDto {

    RequestBodyType apiType;
    String apiUrl;
    Integer statusCode;
    JsonNode responseBody;
}

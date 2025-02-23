package com.easework.simuapi.simu_api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Ritu Raj Verma
 * @apiNote This dto is representing whole local jsonDb file
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiWrapperDto {

    private String dbUrl;
    private Map<String, ApiDetailsDto> apiDetails;
}


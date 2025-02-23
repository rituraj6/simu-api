package com.easework.simuapi.simu_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiDetailsSaveDto extends ApiDetailsDto{

    public String oldApiUrl;
    public com.easework.simuapi.simu_api.enums.RequestBodyType oldApiType;

}

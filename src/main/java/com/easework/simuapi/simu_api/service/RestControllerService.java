package com.easework.simuapi.simu_api.service;

import com.easework.simuapi.simu_api.dto.ApiDetailsSaveDto;
import com.easework.simuapi.simu_api.dto.ResponseDto;
import com.easework.simuapi.simu_api.enums.RequestBodyType;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Ritu Raj Verma
 */

public interface RestControllerService {

    public ResponseDto getMockData(RequestBodyType rbt, String apiId);

    public String saveDataInJsonDB(RequestBodyType rbt, String prevUrlId, String currentUrlId,JsonNode jsonObject);

    public String saveApiDetails(ApiDetailsSaveDto dto);

}

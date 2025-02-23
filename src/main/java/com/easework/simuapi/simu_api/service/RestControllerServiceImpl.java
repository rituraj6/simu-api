package com.easework.simuapi.simu_api.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import com.easework.simuapi.simu_api.dto.ApiDetailsDto;
import com.easework.simuapi.simu_api.dto.ApiDetailsSaveDto;
import com.easework.simuapi.simu_api.dto.ApiWrapperDto;
import com.easework.simuapi.simu_api.dto.ResponseDto;
import com.easework.simuapi.simu_api.enums.RequestBodyType;
import com.easework.simuapi.simu_api.utility.StringLiterals;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Ritu Raj Verma
 * @implNote This implemention is related to fetch and save data in local db
 */

@Slf4j
@Service
public class RestControllerServiceImpl implements RestControllerService{

    @Value("classpath:apidata/mokoonCloneJsonDb.json")
    private Resource resource;

    private ObjectMapper objectMapper = new ObjectMapper();

    public static final Map<Integer, HttpStatus> HTTP_STATUS_CODE_MAP = Arrays.stream(HttpStatus.values())
            .collect(Collectors.toMap(HttpStatus::value, status -> status, (existing, replacement) -> existing));

    @Override
    public ResponseDto getMockData(RequestBodyType rbt, String apiId) {
        apiId = rbt+"_"+apiId;
        log.info("api Id: {}",apiId);
        JsonNode apiResponse = null;
        Integer stausCode = null;

        try {
            File file = resource.getFile();

            if (file.exists() && file.canRead()) {
                ApiWrapperDto apiWrapperDto = objectMapper.readValue(file, ApiWrapperDto.class);
                log.info("apit wrapper dto is coming {}",apiWrapperDto);
                Map<String, ApiDetailsDto> apiDetails = apiWrapperDto.getApiDetails();
                apiResponse = apiDetails != null && apiDetails.get(apiId) != null ? apiDetails.get(apiId).getResponseBody() : apiResponse;
                stausCode = apiDetails != null && apiDetails.get(apiId) != null ? apiDetails.get(apiId).getStatusCode() : 404;
            }
            else{
                log.info("error in file reading");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseDto(HTTP_STATUS_CODE_MAP.get(stausCode), "",apiResponse);
    }

    public JsonNode getJsonNodeByPath(JsonNode jsonObject, String path){
        String[] keyPath = path.split("\\.");
        
        for(String key: keyPath){
            if (jsonObject != null && jsonObject.has(key)) {
               jsonObject = jsonObject.path(key);
            }else{
                return null;
            }
        }
        return jsonObject;
    }

    @Override
    public String saveApiDetails(ApiDetailsSaveDto dto) {
        log.info("Post api is working for save data ");
        StopWatch sw = new StopWatch();
        sw.start("Api details saving started");
        String newApiId = dto.getApiType() + StringLiterals.UNDERSCORE + dto.getApiUrl();
        String oldApiId = dto.getOldApiType() + StringLiterals.UNDERSCORE + dto.getOldApiUrl();
        if(!StringUtils.hasText(newApiId)){
            return "Api should not be blank";
        }
        ApiDetailsDto newApiDetailsdto = new ApiDetailsDto(dto.getApiType(), dto.getApiUrl(), dto.getStatusCode(), dto.getResponseBody());
        ApiWrapperDto allJsonData = new ApiWrapperDto();
        try {
            File dataFile = resource.getFile();
            if(dataFile.exists() && dataFile.canRead()){
                allJsonData = objectMapper.readValue(dataFile, ApiWrapperDto.class);
                Map<String, ApiDetailsDto> apiDetails = allJsonData.getApiDetails();
                ApiDetailsDto apiDetailsDto = apiDetails.get(oldApiId);

                if(apiDetailsDto != null){
                    if(StringUtils.pathEquals(newApiId, oldApiId))
                        apiDetails.put(oldApiId, newApiDetailsdto);
                    else{
                        apiDetails.remove(oldApiId);
                        apiDetails.put(newApiId, newApiDetailsdto);
                    }
                }else{
                    apiDetails.put(newApiId, newApiDetailsdto);
                }
                allJsonData.setApiDetails(apiDetails);
            }else{
                dataFile.createNewFile();
                Map<String, ApiDetailsDto> createNewApiDetails = new HashMap<>();
                createNewApiDetails.put(newApiId, newApiDetailsdto);
                // ApiWrapperDto allJsonData = new ApiWrapperDto();
                allJsonData.setDbUrl("mokoon_clone_devDB");
                allJsonData.setApiDetails(createNewApiDetails);
            }
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(dataFile, allJsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sw.prettyPrint();
        return "Api modified of created successfully";
    }

    @Override
    public String saveDataInJsonDB(RequestBodyType rbt, String prevUrlId, String currentUrlId, JsonNode jsonObject) {
        prevUrlId = rbt+"_"+prevUrlId;
        currentUrlId = rbt+"_"+currentUrlId;
        // File file = new File(dbURL);
        List<String> allApiId = getAllkeyList(jsonObject);
        throw new UnsupportedOperationException("Unimplemented method 'saveDataInJsonDB'");
    }

    public List<String> getAllkeyList(JsonNode jsonnNode){
        
        List<String> keyList = new ArrayList<>();
        if(jsonnNode.isObject()){
            Iterator<Map.Entry<String, JsonNode>> fields = jsonnNode.fields();
            while(fields.hasNext()){
                Map.Entry<String, JsonNode> field = fields.next();
                System.out.println(field.getKey());
                keyList.add(field.getKey());
            }
        }
        return keyList;
    }

    @PreDestroy
    public void saveapiDetailsInResourceApidataJsonFile(){
        
        try {
            File file = resource.getFile();
            if (file.exists() && file.canRead()) {
                ApiWrapperDto apiWrapperDto = objectMapper.readValue(file, ApiWrapperDto.class);
                File newFile = new File("/src/main/resources/apidata/mokoonCloneJsonDb.json");

                if(newFile.exists() && newFile.canRead()){
                    objectMapper.writerWithDefaultPrettyPrinter().writeValue(newFile, file);
                }
            }
        } catch (Exception e) {

        }
    }

}

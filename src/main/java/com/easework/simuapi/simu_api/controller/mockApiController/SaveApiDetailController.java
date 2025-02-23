package com.easework.simuapi.simu_api.controller.mockApiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easework.simuapi.simu_api.dto.ApiDetailsSaveDto;
import com.easework.simuapi.simu_api.service.RestControllerService;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/mockapi")
public class SaveApiDetailController {

    @Autowired
    private RestControllerService rcs;

    @PostMapping("/saveapidetails")
    public ResponseEntity<String> saveApiDetails(@RequestBody ApiDetailsSaveDto dto){
        String msg = rcs.saveApiDetails(dto);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }

}

package com.easework.simuapi.simu_api.controller.mockApiController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easework.simuapi.simu_api.dto.ResponseDto;
import com.easework.simuapi.simu_api.enums.RequestBodyType;
import com.easework.simuapi.simu_api.service.RestControllerService;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.servlet.http.HttpServletRequest;

/**
 * This controller is used to handle all the GET, POST, PUT and DELETE requests.
 * The controller is mapped to "/**" and will handle all the requests that match this pattern.
 * The controller uses the RestControllerService to get the mock data based on the request type and URI.
 * The controller returns the mock data as a JSON response along with the appropriate HTTP status code.
 * @param request The HTTP request object
 * @return The ResponseEntity containing the JSON response and the HTTP status code
 * @author RITU RAJ VERMA
 * @version 1.0
 * @since 1.0
 */

@RestController
public class MasterController {

    @Value("${dbconfig.dbURL}")
    String baseFilePath;

    @Autowired
    RestControllerService restControllerService;

    /**
     * This method is used to handle GET requests.
     * The method uses the RestControllerService to get the mock data based on the request type and URI.
     * The method returns the mock data as a JSON response along with the appropriate HTTP status code.
     * @param request The HTTP request object
     * @return The ResponseEntity containing the JSON response and the HTTP status code
     */
    @GetMapping("/**")
    public ResponseEntity<JsonNode> handleGet(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        System.out.println("GET request received for: " +baseFilePath+" " + requestURI);
        ResponseDto responseDto = restControllerService.getMockData(RequestBodyType.GET, requestURI);
        return new ResponseEntity<>(responseDto.getJsonData(), responseDto.getHttpStatus() != null ?responseDto.getHttpStatus(): HttpStatus.OK);
    }

    @PostMapping("/**")
    public ResponseEntity<JsonNode> handlePost(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        System.out.println("POST request received for: " +baseFilePath+" " + requestURI);
        ResponseDto responseDto = restControllerService.getMockData(RequestBodyType.POST, requestURI);
        return new ResponseEntity<>(responseDto.getJsonData(), responseDto.getHttpStatus());
    }

    @PutMapping("/**")
    public ResponseEntity<JsonNode> handlePut(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        System.out.println("PUT request received for: " +baseFilePath+" " + requestURI);
        ResponseDto responseDto = restControllerService.getMockData(RequestBodyType.PUT, requestURI);
        return new ResponseEntity<>(responseDto.getJsonData(), responseDto.getHttpStatus());
    }

    @DeleteMapping("/**")
    public ResponseEntity<JsonNode> handleDelete(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        System.out.println("DELETE request received for: " +baseFilePath+" " + requestURI);
        ResponseDto responseDto = restControllerService.getMockData(RequestBodyType.DELETE, requestURI);
        return new ResponseEntity<>(responseDto.getJsonData(), responseDto.getHttpStatus());
    }

}

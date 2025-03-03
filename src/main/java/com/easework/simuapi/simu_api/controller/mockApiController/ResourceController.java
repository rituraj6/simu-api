package com.easework.simuapi.simu_api.controller.mockApiController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Value;

/**
 * This controller is used to serve static resources from the "1rLF2DrdC9" folder.
 * The resources are served from the "src/main/resources/static/1rLF2DrdC9" folder.
 * The controller is mapped to "/1rLF2DrdC9/**" and will serve any resource that matches this pattern.
 * For example, "/1rLF2DrdC9/css_1rLF2DrdC9/style.css" will be served from "src/main/resources/static/1rLF2DrdC9/css_1rLF2DrdC9/style.css".
 * The content type of the resource is set to "application/octet-stream" by default. You can change this based on the actual content type.
 * If the resource does not exist, a 404 Not Found response is returned.
 * Note: This controller is used to serve static resources that are not handled by the default resource handler.
 * If you have resources that are handled by the default resource handler, you do not need this controller.
 * @param request The HTTP request object
 * @return The ResponseEntity containing the static resource
 * @author RITU RAJ VERMA
 */
@RestController
public class ResourceController {


    /**
     * This method is used to serve static resources from the "1rLF2DrdC9" folder.
     * The resources are served from the "src/main/resources/static/1rLF2DrdC9" folder.
     * @param request
     * @return
     */
    @GetMapping("/1rLF2DrdC9/**")
    public ResponseEntity<Resource> getStaticResource(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String resourcePath = requestURI.substring("/1rLF2DrdC9".length()); // Remove the "/1rLF2DrdC9" prefix

        // Map the resource path to the static folder
        Path path = Paths.get("src/main/resources/static"+ requestURI);
        System.out.println("path coming: " + path);
        // Check if the resource exists and return it
        if (Files.exists(path)) {
            Resource resource = new FileSystemResource(path.toFile());
            return ResponseEntity.ok()
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


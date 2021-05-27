package com.dlnegoce.exam.service;

import com.dlnegoce.exam.pojo.CatalogPOJO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;

@RestController
public class ExamController {

    @RequestMapping("/catalog")
    public Object catalog() {
        // TODO : load catalog.json file to the appropriate java object and return it
        try {
            File resource = new ClassPathResource(
                    "catalog.json").getFile();
            String json = new String(
                    Files.readAllBytes(resource.toPath()));
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, CatalogPOJO.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/catalog-stats")
    public Object catalogStat() {
        // TODO : calculate the following statistics of the catalog :
        // - total number of nodes
        // - count level 1 and 2 based on level 3 and show results
        // TODO : And return statistics results with the appropriate object
        return null;
    }
}


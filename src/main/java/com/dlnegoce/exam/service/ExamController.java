package com.dlnegoce.exam.service;

import com.dlnegoce.exam.pojo.CatalogPOJO;
import com.dlnegoce.exam.pojo.ItemPOJO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExamController {

    @RequestMapping("/catalog")
    public Object catalog() {
        // TODO : load catalog.json file to the appropriate java object and return it
        try {
            return readCatalog("catalog.json");
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
        try {
            CatalogPOJO catalog = readCatalog("catalog.json");
            List<String> codeLevel1 = new ArrayList<>();
            List<String> codeLevel2 = new ArrayList<>();
            List<String> codeLevel3 = new ArrayList<>();

            for(ItemPOJO childLevel1 : catalog.getChildren())
                for (ItemPOJO childLevel2 : childLevel1.getChildren())
                    for (ItemPOJO childLevel3 : childLevel2.getChildren()){
                        String[] codes = childLevel3.getPath().split("/");
                        if(!codeLevel1.contains(codes[1]))
                            codeLevel1.add(codes[1]);
                        if(!codeLevel2.contains(codes[2]))
                            codeLevel2.add(codes[2]);
                        if(!codeLevel3.contains(codes[3]))
                            codeLevel3.add(codes[3]);
                    }

            return "{" +
                    "\"numberOfNodes\":" + (codeLevel1.size() + codeLevel2.size() + codeLevel3.size()) +
                    ",\"numberOfLevel1\":" + codeLevel1.size() +
                    ",\"numberOfLevel2\":" + codeLevel2.size() +
                    ",\"numberOfLevel3\":" + codeLevel3.size() +
                    "}";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private CatalogPOJO readCatalog(String path) throws Exception{
        File resource = new ClassPathResource(
                path).getFile();
        String json = new String(
                Files.readAllBytes(resource.toPath()));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, CatalogPOJO.class);
    }
}


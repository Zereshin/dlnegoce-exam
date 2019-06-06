package com.dlnegoce.exam.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {

    @RequestMapping("/catalog")
    public Object catalog() {
        // TODO : load catalog.json file to the appropriate java object and return it
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


package com.iskcon.EthicraftAPI.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iskcon.EthicraftAPI.service.UtilService;

@RestController
@CrossOrigin
@RequestMapping("/util")
public class UtilController {

    @Autowired
    UtilService utilService;

    @GetMapping("/populateCollegeData")
    public String populateCollegeData() {
        return utilService.populateCollegeData();
    }

    @GetMapping("/populateMemberData")
    public String populateMemberData() {
        return utilService.populateMember();
    }
}

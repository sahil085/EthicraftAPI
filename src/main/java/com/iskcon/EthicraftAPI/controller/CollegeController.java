package com.iskcon.EthicraftAPI.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iskcon.EthicraftAPI.co.CollegeCO;
import com.iskcon.EthicraftAPI.constants.RoleConstant;
import com.iskcon.EthicraftAPI.dto.CollegeDTO;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
import com.iskcon.EthicraftAPI.service.CollegeService;

@RestController
@CrossOrigin
@RequestMapping("/college")
@PreAuthorize("hasAuthority('"+ RoleConstant.ROLE_ADMIN+"')")
public class CollegeController {


    @Autowired
    private CollegeService collegeService;

    public static final Logger logger = LoggerFactory.getLogger(CollegeController.class);


    @PostMapping("/register")
    public ResponseDTO registerCollege( @RequestBody CollegeCO collegeCO){
        return collegeService.register(collegeCO);
    }

    @GetMapping("/collegeDropDown")
    public List<CollegeDTO> findAllActiveCollegeDropDown(){
        return collegeService.findAllActiveCollege();
    }

}

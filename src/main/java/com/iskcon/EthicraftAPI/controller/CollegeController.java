package com.iskcon.EthicraftAPI.controller;

import com.iskcon.EthicraftAPI.constants.RoleConstant;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
import com.iskcon.EthicraftAPI.co.CollegeCO;
import com.iskcon.EthicraftAPI.service.CollegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

}

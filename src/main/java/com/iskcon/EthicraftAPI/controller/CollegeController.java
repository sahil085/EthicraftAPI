package com.iskcon.EthicraftAPI.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.iskcon.EthicraftAPI.co.CollegeCO;
import com.iskcon.EthicraftAPI.constants.RoleConstant;
import com.iskcon.EthicraftAPI.domain.College;
import com.iskcon.EthicraftAPI.dto.CollegeDTO;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
import com.iskcon.EthicraftAPI.service.CollegeService;
import com.iskcon.EthicraftAPI.service.MailerService;

@RestController
@CrossOrigin
@RequestMapping("/college")

public class CollegeController {


    @Autowired
    private CollegeService collegeService;

    @Autowired
    private MailerService mailerService;

    public static final Logger logger = LoggerFactory.getLogger(CollegeController.class);


    @PostMapping("/register")
    @PreAuthorize("hasAuthority('"+ RoleConstant.ROLE_ADMIN+"')")
    public ResponseDTO registerCollege(@Validated  @RequestBody CollegeCO collegeCO){
        return collegeService.register(collegeCO);
    }

    @GetMapping("/collegeDropDown")
    public List<CollegeDTO> findAllActiveCollegeDropDown(){
        mailerService.prepareAndSend("vermasahil269@gmail.com","Hare Krishna", "member/welcome");
        return collegeService.findAllActiveCollege();
    }

    @GetMapping("/{collegeId}")
    public CollegeDTO findCollegeByID(@PathVariable("collegeId") Long collegeId) {
        return collegeService.findCollegeById(collegeId);
    }

    @GetMapping("/")
    public List<College> findAllColleges() {
        return collegeService.findAllColleges();
    }

    @PutMapping("/{collegeId}")
    @PreAuthorize("hasAuthority('"+ RoleConstant.ROLE_ADMIN+"')")
    public ResponseDTO updateCollegeInfo(@PathVariable("collegeId") Long collegeId, @RequestBody CollegeCO collegeCO) {
        return collegeService.updateCollege(collegeId, collegeCO);
    }
}

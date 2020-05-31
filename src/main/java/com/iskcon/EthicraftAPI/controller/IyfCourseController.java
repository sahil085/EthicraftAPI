package com.iskcon.EthicraftAPI.controller;


import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iskcon.EthicraftAPI.co.IyfCourseCo;
import com.iskcon.EthicraftAPI.domain.IyfCourse;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
import com.iskcon.EthicraftAPI.repository.IyfCourseRepo;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/iyf")
public class IyfCourseController {

    @Autowired
    private IyfCourseRepo iyfCourseRepo;

    @PostMapping("/register")
    public ResponseDTO registerCandidate(@Valid @RequestBody IyfCourseCo iyfCourseCo) {
        try {
            IyfCourse iyfCourse = new ModelMapper().map(iyfCourseCo, IyfCourse.class);
            iyfCourse = iyfCourseRepo.save(iyfCourse);
            iyfCourse.setCandidateId("IYF-"+ generateString() + iyfCourse.getId());
            iyfCourseRepo.save(iyfCourse);
            log.info("Candidate saved :: "+ iyfCourse.getEmail() + " :: ");
            return ResponseDTO.sendSuccessmessage("Candidate register successfully");
        }catch (Exception e){
            log.error(e.getMessage());
            log.info("candidate email :: "+ iyfCourseCo.getEmail() +" mobile :: "+iyfCourseCo.getMobileNumber());
            return ResponseDTO.sendErrorsmessage("Error occured");
        }


    }

    private String generateString(){
        return RandomStringUtils.randomAlphabetic(5);
    }

}

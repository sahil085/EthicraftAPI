package com.iskcon.EthicraftAPI.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iskcon.EthicraftAPI.co.CollegeCourseStatusCO;
import com.iskcon.EthicraftAPI.co.CourseCO;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
import com.iskcon.EthicraftAPI.exception.UnAuthorizeException;
import com.iskcon.EthicraftAPI.service.CourseService;
import com.iskcon.EthicraftAPI.service.ThreadRequestService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO create(@Valid  @RequestBody CourseCO courseCO){
        return courseService.create(null, courseCO);
    }

    @PutMapping(value = "/approve/{courseId}")
    public ResponseDTO approve(@PathVariable("courseId") Long courseId){
        if(!ThreadRequestService.fetchCurrentRole().isAdmin()){
            throw new UnAuthorizeException("");
        }
        return courseService.approveCourse(courseId);
    }

    @PutMapping(value = "/classTimings")
    public ResponseDTO classTimings(@Valid @RequestBody CourseCO courseCO){
        if(!ThreadRequestService.fetchCurrentRole().isCA()){
            throw new UnAuthorizeException("");
        }
        return courseService.insertClassTimings(courseCO);
    }
    @PutMapping(value = "/update")
    public ResponseDTO updateCourse(@Valid @RequestBody CourseCO courseCO){
        /* We can give allow CA to update course details if required in future */
        if(ThreadRequestService.fetchCurrentRole().isAdmin()){
            return courseService.updateCourse(courseCO);
        }else {
            throw new UnAuthorizeException("");
        }
    }

    @PutMapping(value = "/collegeStatus" )
    public ResponseDTO updateCollegeStatus(@Valid @RequestBody CollegeCourseStatusCO collegeCourseStatusCO){
        if(!ThreadRequestService.fetchCurrentRole().isAdmin()){
            throw new UnAuthorizeException("");
        }
        return courseService.updateCollegeCourseStatus(collegeCourseStatusCO);
    }

}

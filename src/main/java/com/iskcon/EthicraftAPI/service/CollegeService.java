package com.iskcon.EthicraftAPI.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.iskcon.EthicraftAPI.co.CollegeCO;
import com.iskcon.EthicraftAPI.domain.College;
import com.iskcon.EthicraftAPI.dto.CollegeDTO;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
import com.iskcon.EthicraftAPI.repository.CollegeRepository;

@Service
public class CollegeService {

    public static final Logger logger = LoggerFactory.getLogger(CollegeService.class);

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private MailerService mailerService;

    @Autowired
    private SendGridMailService sendGridMailService;

    public ResponseDTO register(CollegeCO collegeCO){
            try{
                ModelMapper modelMapper = new ModelMapper();
                College college = modelMapper.map(collegeCO, College.class);
                if(isValid(college)){
                    collegeRepository.saveAndFlush(college);
                    return new ResponseDTO().createSuccessMessage("College registered successfully",null,HttpStatus.OK.value(),"success");
                }else
                {
                    return new ResponseDTO().createErrorMessage("College Name must be unique",null,HttpStatus.CONFLICT.value(),"error");

                }
            }catch (Exception e){
                e.printStackTrace();
                return ResponseDTO.sendErrorsmessage("Internal server error");
            }

    }

    private Boolean isValid(College college){
        return collegeRepository.countByCollegeName(college.getCollegeName()) == 0 ;
    }

    public College findByCollegeId(Long id){
        return collegeRepository.getOne(id);
    }

    public List<CollegeDTO> findAllActiveCollege() {
//        mailerService.prepareAndSend("vermasahil.269@gmail.com","Hare Krishna","member/welcome");
        sendGridMailService.sendEmailUsingSendGrid();
        ModelMapper modelMapper = new ModelMapper();
        List<College> collegeList =  collegeRepository.findAllByIsActive(true);
        return modelMapper.map(collegeList, new TypeToken<List<CollegeDTO>>(){}.getType());
    }



}

package com.iskcon.EthicraftAPI.service;

import java.text.CollationElementIterator;
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
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.sendErrorsmessage("Internal server error");
        }

    }

    private Boolean isValid(College college) {
        return collegeRepository.countByCollegeName(college.getCollegeName()) == 0;
    }

    public College findByCollegeId(Long id) {
        return collegeRepository.getOne(id);
    }

    public CollegeDTO findCollegeById(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        College college = collegeRepository.findById(id).orElse(null);
        if (college != null) {
            return modelMapper.map(college, new TypeToken<CollegeDTO>() {
            }.getType());
        }
        return null;
    }

    public List<CollegeDTO> findAllActiveCollege() {
//        mailerService.prepareAndSend("vermasahil.269@gmail.com","Hare Krishna","member/welcome");
        sendGridMailService.sendEmailUsingSendGrid();
        ModelMapper modelMapper = new ModelMapper();
        List<College> collegeList = collegeRepository.findAllByIsActive(true);
        return modelMapper.map(collegeList, new TypeToken<List<CollegeDTO>>() {
        }.getType());
    }

    public List<CollegeDTO> findAllColleges() {
        ModelMapper modelMapper = new ModelMapper();
        List<College> collegeList = collegeRepository.findAll();
        return modelMapper.map(collegeList, new TypeToken<List<CollegeDTO>>() {
        }.getType());
    }

    public ResponseDTO updateCollege(Long collegeId, CollegeCO collegeCO) {
        try {
            College updatedCollege = collegeRepository.findById(collegeId).orElse(null);
            if (updatedCollege == null) {
                return ResponseDTO.sendErrorsmessage("College not found");
            } else {
                ModelMapper modelMapper = new ModelMapper();
                updatedCollege = modelMapper.map(collegeCO, College.class);
                if (isValid(updatedCollege)) {
                    updatedCollege.setId(collegeId);
                    collegeRepository.saveAndFlush(updatedCollege);
                    return ResponseDTO.sendSuccessmessage("College updated successfully");
                } else {
                    return ResponseDTO.sendErrorsmessage("College Name must be unique");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.sendErrorsmessage("Internal server error");
        }
    }

}

package com.iskcon.EthicraftAPI.service;

import com.iskcon.EthicraftAPI.co.AddressCO;
import com.iskcon.EthicraftAPI.co.CollegeCO;
import com.iskcon.EthicraftAPI.domain.College;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
import com.iskcon.EthicraftAPI.repository.CollegeRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
public class CollegeService {

    public static final Logger logger = LoggerFactory.getLogger(CollegeService.class);

    @Autowired
    private CollegeRepository collegeRepository;

    public ResponseDTO register(CollegeCO collegeCO){

            ModelMapper modelMapper = new ModelMapper();
            College college = modelMapper.map(collegeCO, College.class);
            if(isValid(college)){
                collegeRepository.saveAndFlush(college);
                return new ResponseDTO().createSuccessMessage("College registered successfully",null,HttpStatus.OK.value(),"success");
            }else
            {
                return new ResponseDTO().createErrorMessage("College Name must be unique",null,HttpStatus.CONFLICT.value(),"error");

            }
    }

    private Boolean isValid(College college){
        return collegeRepository.countByCollegeName(college.getCollegeName()) == 0 ;
    }

}

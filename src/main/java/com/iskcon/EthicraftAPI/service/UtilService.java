package com.iskcon.EthicraftAPI.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iskcon.EthicraftAPI.domain.College;
import com.iskcon.EthicraftAPI.domain.CollegeReference;
import com.iskcon.EthicraftAPI.domain.Member;
import com.iskcon.EthicraftAPI.dto.CollegeExcelDTO;
import com.iskcon.EthicraftAPI.dto.MemberExcelDTO;
import com.iskcon.EthicraftAPI.repository.CollegeRepository;
import com.iskcon.EthicraftAPI.repository.MemberRepository;

@Service
public class UtilService {

    @Autowired
    CollegeRepository collegeRepository;

    @Autowired
    MemberRepository memberRepository;

    public String populateCollegeData() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<CollegeExcelDTO>> typeReference = new TypeReference<List<CollegeExcelDTO>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/collegedata.json");
        try {
            List<CollegeExcelDTO> collegeDTOList = mapper.readValue(inputStream, typeReference);
            ModelMapper modelMapper = new ModelMapper();
            collegeDTOList.forEach(collegeDTO -> {
                if(collegeRepository.countByCollegeName(collegeDTO.getCollegeName()) == 0){

                    System.out.println("----------------------->  "+collegeDTO.getCollegeName());
                    College college =  modelMapper.map(collegeDTO, College.class);
                    if(collegeDTO.getReferenceName() != null){
                        CollegeReference collegeReference = new CollegeReference();
                        collegeReference.setName(collegeDTO.getReferenceName());
                        if(!StringUtils.isEmpty(collegeDTO.getReferenceContact())){
                            collegeReference.setContact(Long.parseLong(collegeDTO.getReferenceContact()));
                        }else {
                            collegeReference.setContact(9999999999l);
                        }
                        collegeReference.setDesignation(collegeDTO.getReferenceDesignation());
                        college.setReferenceList(Collections.singletonList(collegeReference));
                    }
                    if(StringUtils.isEmpty(college.getAddress())) {
                        college.setAddress("System Address");
                    }
                    if(StringUtils.isEmpty(college.getState())) {
                        college.setState("System state");
                    }
                    if(StringUtils.isEmpty(college.getCity())) {
                        college.setCity("system city");
                    }
                    if(StringUtils.isEmpty(college.getUniversityName())) {
                        college.setUniversityName("System University");
                    }
                    collegeRepository.saveAndFlush(college);

                }

            });
            return "College data populated successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Some occur while populating college data";
        }
    }


    public String populateMember() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<MemberExcelDTO>> typeReference = new TypeReference<List<MemberExcelDTO>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/memberData.json");
        try {
            List<MemberExcelDTO> memberExcelDTOList = mapper.readValue(inputStream, typeReference);
            ModelMapper modelMapper = new ModelMapper();
            memberExcelDTOList.forEach(memberExcelDTO -> {
                System.out.println(memberExcelDTO.toString());
                System.out.println("---------------------------------------");
                Member member = modelMapper.map(memberExcelDTO, Member.class);
                System.out.println(member.toString());
                System.out.println("--------------------------------------");
            });

            return "Member data populated successfully";
        }catch (Exception e){
         e.printStackTrace();
            return "Some occur while populating member data";
        }
    }

    //TODO to be implemented later
    public static void uploadFile(MultipartFile file){
        return;
    }

    public static LocalDate convertDateToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date convertLocalDateToDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}

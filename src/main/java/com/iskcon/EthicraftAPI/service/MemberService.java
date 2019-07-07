package com.iskcon.EthicraftAPI.service;

import com.iskcon.EthicraftAPI.co.MemberShipFormCO;
import com.iskcon.EthicraftAPI.domain.College;
import com.iskcon.EthicraftAPI.domain.Member;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    public static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberService memberService;
    @Autowired
    private CollegeService collegeService;

    public ResponseDTO createMember(MemberShipFormCO memberShipFormCO){
        ModelMapper modelMapper = new ModelMapper();
        Member member = modelMapper.map(memberShipFormCO, Member.class);
        College college = collegeService.findByCollegeId(memberShipFormCO.getCollegeId());
        return null;
    }

}

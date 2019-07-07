package com.iskcon.EthicraftAPI.service;

import com.iskcon.EthicraftAPI.co.MemberShipFormCO;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    public static final Logger logger = LoggerFactory.getLogger(MemberService.class);


    public ResponseDTO createMember(MemberShipFormCO memberShipFormCO){
        throw new RuntimeException("error");
    }

}

package com.iskcon.EthicraftAPI.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iskcon.EthicraftAPI.domain.College;
import com.iskcon.EthicraftAPI.domain.Role;
import com.iskcon.EthicraftAPI.domain.User;
import com.iskcon.EthicraftAPI.domain.UserRoleCollegeMapping;
import com.iskcon.EthicraftAPI.repository.CollegeRepository;
import com.iskcon.EthicraftAPI.repository.UserRoleCollegeRepo;

@Service
public class CommonService {

    @Autowired
    CollegeRepository collegeRepository;

    @Autowired
    UserRoleCollegeRepo userRoleCollegeRepo;

    @Autowired
    SendGridMailService sendGridMailService;

    public void createUserRoleCollegeMapping(User user, Role role, List<Long> collegeIds) {
        UserRoleCollegeMapping userRoleCollegeMapping = new UserRoleCollegeMapping();
        if(collegeIds != null){
            List<College> colleges = collegeRepository.findAllByIdIn(collegeIds);
            userRoleCollegeMapping.setCollegeList(colleges);
        }
        userRoleCollegeMapping.setRole(role);
        userRoleCollegeMapping.setUser(user);
        userRoleCollegeRepo.saveAndFlush(userRoleCollegeMapping);
    }
}

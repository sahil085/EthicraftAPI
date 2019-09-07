package com.iskcon.EthicraftAPI.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iskcon.EthicraftAPI.co.AssignRoleCO;
import com.iskcon.EthicraftAPI.co.MemberShipFormCO;
import com.iskcon.EthicraftAPI.constants.RoleConstant;
import com.iskcon.EthicraftAPI.domain.College;
import com.iskcon.EthicraftAPI.domain.Member;
import com.iskcon.EthicraftAPI.domain.Role;
import com.iskcon.EthicraftAPI.domain.User;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
import com.iskcon.EthicraftAPI.repository.CollegeRepository;
import com.iskcon.EthicraftAPI.repository.MemberRepository;
import com.iskcon.EthicraftAPI.repository.RoleRepository;
import com.iskcon.EthicraftAPI.repository.UserRepository;

@Service
public class MemberService {
    public static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CollegeService collegeService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollegeRepository collegeRepository;


    public ResponseDTO createMember(MemberShipFormCO memberShipFormCO){
        try{
            if (isUniqueEmail(memberShipFormCO.getEmail())){
                ModelMapper modelMapper = new ModelMapper();
                Member member = modelMapper.map(memberShipFormCO, Member.class);
                College college = collegeService.findByCollegeId(memberShipFormCO.getCollegeId());
                member.setCollege(college);
                member.setProfilePic("profile pic");
                memberRepository.saveAndFlush(member);
                return ResponseDTO.sendSuccessmessage("Cadidate registered successfully");
            }else {
               return ResponseDTO.sendErrorsmessage("Email Id already exist");
            }

        }catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException("Some error occured while registration");
        }

    }

    private Boolean isUniqueEmail(String email){
        return memberRepository.countByEmail(email) == 0;
    }

    public ResponseDTO assignRoleToUser(AssignRoleCO assignRoleCO) {
        try {
            User user = userRepository.findByEmail(assignRoleCO.getUsername());
            Set<Role> userRoles = user.getRoles();
            Role role = roleRepository.findByRole(assignRoleCO.getRole());
            Member member = memberRepository.findByEmail(assignRoleCO.getUsername());
            if(userRoles.contains(role)){
                return ResponseDTO.sendErrorsmessage("User has already "+role.getRole()+" role");
            }else if(assignRoleCO.getRole().equals(RoleConstant.ROLE_CA)){
                Set<Role> roles = new HashSet<>();
                List<College> collegeList = Collections.singletonList(member.getCollege());
                role.setCollegeList(collegeList);
                roles.add(role);
                user.setRoles(roles);
                userRepository.saveAndFlush(user);
                return ResponseDTO.sendSuccessmessage("Role successfully assigned to "+user.getUsername());
            } else if (assignRoleCO.getRole().equals(RoleConstant.ROLE_EEO)) {
                Set<Role> roles = new HashSet<>();
                List<College> collegeList = collegeRepository.findAllByIdIn(assignRoleCO.getColleges());
                role.setCollegeList(collegeList);
                roles.add(role);
                user.setRoles(roles);
                userRepository.saveAndFlush(user);
                return ResponseDTO.sendSuccessmessage("Role successfully assigned to "+user.getUsername());
            } else {
                return ResponseDTO.sendSuccessmessage("");
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDTO.sendErrorsmessage("Internal Server Error");
        }
    }

}

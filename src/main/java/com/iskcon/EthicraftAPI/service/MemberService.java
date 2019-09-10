package com.iskcon.EthicraftAPI.service;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.iskcon.EthicraftAPI.co.MemberShipFormCO;
import com.iskcon.EthicraftAPI.constants.RoleConstant;
import com.iskcon.EthicraftAPI.domain.College;
import com.iskcon.EthicraftAPI.domain.Member;
import com.iskcon.EthicraftAPI.domain.Role;
import com.iskcon.EthicraftAPI.domain.User;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
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
    private CommonService commonService;


    public ResponseDTO createMember(MemberShipFormCO memberShipFormCO){
        try{
            if (isUniqueEmail(memberShipFormCO.getEmail())){
                ModelMapper modelMapper = new ModelMapper();
                Member member = modelMapper.map(memberShipFormCO, Member.class);
                College college = collegeService.findByCollegeId(memberShipFormCO.getCollegeId());
                member.setId(null);
                member.setCollege(college);
                member.setProfilePic("profile pic");
                memberRepository.saveAndFlush(member);
                User userInfo1 = userRepository.findByEmail(member.getEmail());
                if(userInfo1==null){
                    User userInfo = new User();
                    userInfo.setEmail(member.getEmail());
                    userInfo.setPassword(new BCryptPasswordEncoder().encode(member.getPassword()));
                    userInfo.setUsername(member.getFirstName() + " " + member.getMiddleName() + member.getLastName());
                    Set<Role> roles = new HashSet<>();
                    Role role = roleRepository.findByRole(RoleConstant.ROLE_USER);
                    roles.add(role);
                    userInfo.setRoles(roles);
                    userRepository.saveAndFlush(userInfo);
                    commonService.createUserRoleCollegeMapping(userInfo, role, null);
                    System.out.println(" ****  User Created  ****");
                }
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



}

package com.iskcon.EthicraftAPI.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iskcon.EthicraftAPI.co.AssignRoleCO;
import com.iskcon.EthicraftAPI.constants.RoleConstant;
import com.iskcon.EthicraftAPI.domain.College;
import com.iskcon.EthicraftAPI.domain.Member;
import com.iskcon.EthicraftAPI.domain.Role;
import com.iskcon.EthicraftAPI.domain.User;
import com.iskcon.EthicraftAPI.domain.UserRoleCollegeMapping;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
import com.iskcon.EthicraftAPI.dto.UserRoleCollegeMappingDTO;
import com.iskcon.EthicraftAPI.repository.CollegeRepository;
import com.iskcon.EthicraftAPI.repository.MemberRepository;
import com.iskcon.EthicraftAPI.repository.RoleRepository;
import com.iskcon.EthicraftAPI.repository.UserRepository;
import com.iskcon.EthicraftAPI.repository.UserRoleCollegeRepo;

@Service
public class UserAccountService {


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserRoleCollegeRepo userRoleCollegeRepo;

    @Autowired
    private CollegeRepository collegeRepository;



    public ResponseDTO assignRoleToUser(AssignRoleCO assignRoleCO) {
        try {
            User user = userRepository.findByEmail(assignRoleCO.getUsername());
            Set<Role> userRoles = user.getRoles();
            Role role = roleRepository.findByRole(assignRoleCO.getRole());
            Member member = memberRepository.findByEmail(assignRoleCO.getUsername());
            if(userRoles.contains(role)){
                return ResponseDTO.sendErrorsmessage("User has already "+role.getRole()+" role");
            }else if(assignRoleCO.getRole().equals(RoleConstant.ROLE_CA)){
                List<Long> collegeList = Collections.singletonList(member.getCollege().getId());
                commonService.createUserRoleCollegeMapping(user,role,collegeList);
                userRoles.add(role);
                user.setRoles(userRoles);
                userRepository.saveAndFlush(user);
                return ResponseDTO.sendSuccessmessage("Role successfully assigned to "+user.getUsername());
            } else if (assignRoleCO.getRole().equals(RoleConstant.ROLE_EEO)) {
                userRoles.add(role);
                user.setRoles(userRoles);
                commonService.createUserRoleCollegeMapping(user,role,assignRoleCO.getColleges());
                userRepository.saveAndFlush(user);
                return ResponseDTO.sendSuccessmessage("Role successfully assigned to "+user.getUsername());
            }else {
                userRoles.add(role);
                user.setRoles(userRoles);
                commonService.createUserRoleCollegeMapping(user,role,null);
                userRepository.saveAndFlush(user);
            }
            return ResponseDTO.sendSuccessmessage("Role successfully assigned to "+user.getUsername());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDTO.sendErrorsmessage("Internal Server Error");
        }
    }



    public List<UserRoleCollegeMappingDTO> findAllUserRoleMapping() {
        try {
            List<UserRoleCollegeMapping> userRoleCollegeMappingList = userRoleCollegeRepo.findAll();
            return UserRoleCollegeMapping.mapToDTO(userRoleCollegeMappingList);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error while fetching User Role");
        }
    }

    public ResponseDTO deleteUserRoleCollege(Long id) {
        try {
            UserRoleCollegeMapping userRoleCollegeMapping = userRoleCollegeRepo.getOne(id);
            if(userRoleCollegeMapping != null) {
                User user = userRoleCollegeMapping.getUser();
                Role role = userRoleCollegeMapping.getRole();
                if(role.getRole().equals(RoleConstant.ROLE_MEMBER)){
                    Member member = memberRepository.findByEmail(user.getEmail());
                    if(member != null){
                        member.setMemberApproved(false);
                        memberRepository.save(member);
                    }
                }
                user.getRoles().removeIf(role1 -> role1.getRole().equals(role.getRole()));
                userRepository.saveAndFlush(user);
                userRoleCollegeRepo.delete(userRoleCollegeMapping);
                return ResponseDTO.sendSuccessmessage("User role deleted successfully");
            }else {
                return ResponseDTO.sendErrorsmessage("No User role mapping found");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDTO.sendErrorsmessage("Error in deleting user role");
        }
    }


    public UserRoleCollegeMapping findOneById(Long id){
        try {
            return userRoleCollegeRepo.findById(id).orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Internal Server error");
        }
    }

    public ResponseDTO updateUserRoleMapping(AssignRoleCO assignRoleCO){

        try{
            if(assignRoleCO.getRole().equals(RoleConstant.ROLE_EEO) && assignRoleCO.getColleges() != null && !assignRoleCO.getColleges().isEmpty()) {
                UserRoleCollegeMapping userRoleCollegeMapping = userRoleCollegeRepo.findById(assignRoleCO.getId()).get();
                List<College> colleges = collegeRepository.findAllByIdIn(assignRoleCO.getColleges());
                userRoleCollegeMapping.setCollegeList(colleges);
                userRoleCollegeRepo.saveAndFlush(userRoleCollegeMapping);
                return ResponseDTO.sendSuccessmessage("User role college mapping successfully updated");
            }else {
                return ResponseDTO.sendErrorsmessage("Update operation does not exist for the selected role");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Internal server error");
        }
    }
}

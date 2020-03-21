package com.iskcon.EthicraftAPI.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iskcon.EthicraftAPI.co.AssignRoleCO;
import com.iskcon.EthicraftAPI.co.MemberShipFormCO;
import com.iskcon.EthicraftAPI.co.QuickMembershipCO;
import com.iskcon.EthicraftAPI.constants.RoleConstant;
import com.iskcon.EthicraftAPI.domain.Address;
import com.iskcon.EthicraftAPI.domain.College;
import com.iskcon.EthicraftAPI.domain.Member;
import com.iskcon.EthicraftAPI.domain.Role;
import com.iskcon.EthicraftAPI.domain.User;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
import com.iskcon.EthicraftAPI.repository.MemberRepository;
import com.iskcon.EthicraftAPI.repository.RoleRepository;
import com.iskcon.EthicraftAPI.repository.UserRepository;
import com.iskcon.EthicraftAPI.securityservices.CurrentUser;

@Service
@Transactional

public class MemberService {
    public static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CollegeService   collegeService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private MailerService mailerService;

    public ResponseDTO createMember(MemberShipFormCO memberShipFormCO) {
        try {
            if (isUniqueEmail(memberShipFormCO.getEmail())) {
                ModelMapper modelMapper = new ModelMapper();
                Member member = modelMapper.map(memberShipFormCO, Member.class);
                College college = collegeService.findByCollegeId(memberShipFormCO.getCollegeId());
                member.setId(null);
                member.setCollege(college);
                member.setProfilePic("profile pic");
                Random random = new Random();

                member.setMembershipId("ETHIC-" + random.nextInt(10000) + member.getMobileNumber().toString().substring(5));

                member = memberRepository.saveAndFlush(member);
                ;
                User userInfo1 = userRepository.findByEmail(member.getEmail());
                if (userInfo1 == null) {
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
            } else {
                return ResponseDTO.sendErrorsmessage("Email Id already exist");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Some error occured while registration");
        }

    }

    public ResponseDTO quickRegister(QuickMembershipCO quickMembershipCO) {
        try {
            if (isUniqueEmail(quickMembershipCO.getEmail())) {
                ModelMapper modelMapper = new ModelMapper();
                Member member = modelMapper.map(quickMembershipCO, Member.class);
                Address address =modelMapper.map(quickMembershipCO.getAddressObject(), Address.class);
                member.setPermanentAddress(address);
                member.setPresentAddress(address);
                member.setCourseName("Dummy");
                member.setBatch("Dummy");
                if(quickMembershipCO.getUnRegisteredCollege() != null){
                    member.setUnRegisteredCollege(quickMembershipCO.getUnRegisteredCollege());

                }else {
                    College college = collegeService.findByCollegeId(quickMembershipCO.getCollege());
                    member.setCollege(college);
                }
                member.setId(null);
                member.setProfilePic("profile pic");
                member.setPassword(member.getFirstName() + member.getMobileNumber().toString().substring(0,4));
                Random random = new Random();

                member.setMembershipId("ETHIC-" + random.nextInt(10000) + member.getMobileNumber().toString().substring(5));

                member = memberRepository.saveAndFlush(member);
                ;
                User userInfo1 = userRepository.findByEmail(member.getEmail());
                if (userInfo1 == null) {
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
                return ResponseDTO.sendSuccessmessage("Registration done successfully");
            } else {
                return ResponseDTO.sendErrorsmessage("Email Id already exist");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Some error occured while registration");
        }

    }

    private Boolean isUniqueEmail(String email) {
        return memberRepository.countByEmail(email) == 0;
    }

    public List<Member> findAllPendingMembers(String currentRole) {
        try {
            switch (currentRole) {
                case RoleConstant.ROLE_CA:
                    User user = CurrentUser.getCurrentUser();
                    Member member = memberRepository.findByEmail(user.getEmail());
                    return memberRepository.findAllByIsMemberApprovedAndCollege(false, member.getCollege());
                case RoleConstant.ROLE_ADMIN:
                    return memberRepository.findAllByIsMemberApproved(false);
                default:
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Internal server error");
        }
    }
    public List<Member> findAll(String currentRole) {
        try {
            switch (currentRole) {
                case RoleConstant.ROLE_CA:
                    User user = CurrentUser.getCurrentUser();
                    Member member = memberRepository.findByEmail(user.getEmail());
                    return memberRepository.findAllByCollege(member.getCollege());
                case RoleConstant.ROLE_ADMIN:
                    return memberRepository.findAll();
                default:
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Internal server error");
        }
    }

    public ResponseDTO approveOrDeclineMember(Long memberId, Boolean approveStatus) {
        try {
            Member member = memberRepository.findById(memberId).orElse(null);
            if (member != null) {
                member.setMemberApproved(approveStatus);
                memberRepository.saveAndFlush(member);
                if (approveStatus) {
                    AssignRoleCO assignRoleCO = new AssignRoleCO();
                    assignRoleCO.setColleges(null);
                    assignRoleCO.setRole(RoleConstant.ROLE_MEMBER);
                    assignRoleCO.setUsername(member.getEmail());
                    userAccountService.assignRoleToUser(assignRoleCO);
                    Object data = new HashMap<>();
                    ((HashMap) data).put("username",member.getFirstName() + " " + member.getLastName());
                    ((HashMap) data).put("membershipId",member.getMembershipId());
                    mailerService.prepareAndSend(member.getEmail(),data,"member/welcome");
                    return ResponseDTO.sendSuccessmessage("User approved as member successfully");
                } else {

                    return ResponseDTO.sendSuccessmessage("User is no longer a member of ethiccraft family");
                }
            } else {
                return ResponseDTO.sendErrorsmessage("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Internal server error");
        }
    }

    public College findMemberCollege(String email){
        return memberRepository.findByEmail(email).getCollege();
    }


}

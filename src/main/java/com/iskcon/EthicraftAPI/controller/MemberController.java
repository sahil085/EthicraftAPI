package com.iskcon.EthicraftAPI.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iskcon.EthicraftAPI.co.MemberShipFormCO;
import com.iskcon.EthicraftAPI.constants.RoleConstant;
import com.iskcon.EthicraftAPI.domain.Member;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
import com.iskcon.EthicraftAPI.repository.RoleRepository;
import com.iskcon.EthicraftAPI.securityservices.UserService;
import com.iskcon.EthicraftAPI.service.MemberService;

@RestController
@CrossOrigin
@RequestMapping("/member")
public class MemberController {

    public static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    MemberService  memberService;
    @Autowired
    UserService    userService;
    @Autowired
    RoleRepository roleRepository;


    @PostMapping("/register")
    public ResponseDTO registerNewMember(@Valid @RequestBody MemberShipFormCO memberShipFormCO) {
        return memberService.createMember(memberShipFormCO);
    }

    @GetMapping("/roles")
    public List<String> getAllRoles() {
        return RoleConstant.ROLES;
    }

    @GetMapping("/findAllEmail")
    public List<String> findAllMembersEmail() {
        return userService.findAllEmails();
    }


    @GetMapping("/pending-members/{currentRole}")
    public List<Member> findAllPendingMember(@PathVariable("currentRole") String currentRole) {
        return memberService.findAllPendingMembers(currentRole);
    }

    @PutMapping("/approveOrDecline/{memberId}/{approveStatus}")
    public ResponseDTO approveMember(@Validated @NotNull @PathVariable("memberId") Long memberId,
                                     @Validated @NotNull @PathVariable("approveStatus") boolean approveStatus){
        return memberService.approveOrDeclineMember(memberId, approveStatus);
    }

}

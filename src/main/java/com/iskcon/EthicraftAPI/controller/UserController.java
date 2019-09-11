package com.iskcon.EthicraftAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iskcon.EthicraftAPI.co.AssignRoleCO;
import com.iskcon.EthicraftAPI.domain.UserRoleCollegeMapping;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
import com.iskcon.EthicraftAPI.dto.UserRoleCollegeMappingDTO;
import com.iskcon.EthicraftAPI.service.UserAccountService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserAccountService userAccountService;

    @PutMapping("/assignRole")
    public ResponseDTO assignRole(@Validated @RequestBody AssignRoleCO assignRoleCO) {
        return userAccountService.assignRoleToUser(assignRoleCO);
    }

    @GetMapping("/findAllUserRole")
    public List<UserRoleCollegeMappingDTO> findAllUserRole() {
        return userAccountService.findAllUserRoleMapping();
    }

    @DeleteMapping("/deleteRole/{id}")
    public ResponseDTO deleteUserRole(@PathVariable("id") Long id) {
        return userAccountService.deleteUserRoleCollege(id);
    }

    @GetMapping("/userRoleById/{id}")
    public UserRoleCollegeMapping findById(@PathVariable("id") Long id) {
        return userAccountService.findOneById(id);
    }

    @PutMapping("/updateUserRole")
    public ResponseDTO updateUserRole(@Validated @RequestBody AssignRoleCO assignRoleCO){
            return userAccountService.updateUserRoleMapping(assignRoleCO);
    }



}

package com.iskcon.EthicraftAPI.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iskcon.EthicraftAPI.dto.UserRoleCollegeMappingDTO;

@Entity
@Table(name = "user_role_college_mapping")
public class UserRoleCollegeMapping implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;
    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Role role;

    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<College> collegeList;

    public Long getId() {
        return id;
    }

    public UserRoleCollegeMapping setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserRoleCollegeMapping setUser(User user) {
        this.user = user;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserRoleCollegeMapping setRole(Role role) {
        this.role = role;
        return this;
    }

    public List<College> getCollegeList() {
        return collegeList;
    }

    public UserRoleCollegeMapping setCollegeList(List<College> collegeList) {
        this.collegeList = collegeList;
        return this;
    }

    public static List<UserRoleCollegeMappingDTO> mapToDTO(List<UserRoleCollegeMapping> userRoleCollegeMappingList){
        List<UserRoleCollegeMappingDTO> userRoleCollegeMappingDTOList = new ArrayList<>();
        userRoleCollegeMappingList.forEach(userRoleCollegeMapping -> {
            UserRoleCollegeMappingDTO userRoleCollegeMappingDTO = new UserRoleCollegeMappingDTO();
            if(userRoleCollegeMapping.getCollegeList() != null){
                String collegeNames =userRoleCollegeMapping.getCollegeList().stream().map(College::getCollegeName).collect(Collectors.joining(","));
                userRoleCollegeMappingDTO.setCollegeName(collegeNames);
            }

            userRoleCollegeMappingDTO.setId(userRoleCollegeMapping.getId());
            userRoleCollegeMappingDTO.setRole(userRoleCollegeMapping.getRole().getRole());
            userRoleCollegeMappingDTO.setUserEmail(userRoleCollegeMapping.getUser().getEmail());
            userRoleCollegeMappingDTO.setUserName(userRoleCollegeMapping.getUser().getUsername());
            userRoleCollegeMappingDTOList.add(userRoleCollegeMappingDTO);

        });
        return userRoleCollegeMappingDTOList;
    }
}

package com.iskcon.EthicraftAPI.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserRoleCollegeMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private User user;

    private Role role;

    @OneToMany
    private List<College> collegeIds = new ArrayList<>();

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

    public List<College> getCollegeIds() {
        return collegeIds;
    }

    public UserRoleCollegeMapping setCollegeIds(List<College> collegeIds) {
        this.collegeIds = collegeIds;
        return this;
    }
}

package com.iskcon.EthicraftAPI.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;

@Entity
public class Role extends BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String role;

    @OneToMany
    List<College> collegeList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<College> getCollegeList() {
        return collegeList;
    }

    public Role setCollegeList(List<College> collegeList) {
        this.collegeList = collegeList;
        return this;
    }
}

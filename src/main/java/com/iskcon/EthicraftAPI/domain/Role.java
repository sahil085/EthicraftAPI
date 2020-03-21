package com.iskcon.EthicraftAPI.domain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

import com.iskcon.EthicraftAPI.constants.RoleConstant;

@Entity
@Embeddable
@Table(name = "role")
public class Role extends BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String role;


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

    public Boolean isCA(){
        return this.role.equals(RoleConstant.ROLE_CA);
    }

    public Boolean isAdmin(){
        return this.role.equals(RoleConstant.ROLE_ADMIN);
    }

    public Boolean isEEO(){
        return this.role.equals(RoleConstant.ROLE_EEO);
    }

    public Boolean isMember(){
        return this.role.equals(RoleConstant.ROLE_MEMBER);
    }

    public Boolean isSuperAdmin(){
        return this.role.equals(RoleConstant.ROLE_SUPER_ADMIN);
    }

    public Boolean isUser(){
        return this.role.equals(RoleConstant.ROLE_USER);
    }
}

package com.iskcon.EthicraftAPI.co;

import javax.validation.constraints.NotNull;

import java.util.List;

public class AssignRoleCO {

    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String role;

    private List<Long> colleges;

    public Long getId() {
        return id;
    }

    public AssignRoleCO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AssignRoleCO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getRole() {
        return role;
    }

    public AssignRoleCO setRole(String role) {
        this.role = role;
        return this;
    }

    public List<Long> getColleges() {
        return colleges;
    }

    public AssignRoleCO setColleges(List<Long> colleges) {
        this.colleges = colleges;
        return this;
    }
}

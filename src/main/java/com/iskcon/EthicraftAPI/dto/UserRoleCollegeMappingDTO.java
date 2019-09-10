package com.iskcon.EthicraftAPI.dto;

public class UserRoleCollegeMappingDTO {

    private Long id;
    private String role;
    private String userName;
    private String userEmail;
    private String collegeName;

    public Long getId() {
        return id;
    }

    public UserRoleCollegeMappingDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserRoleCollegeMappingDTO setRole(String role) {
        this.role = role;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserRoleCollegeMappingDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserRoleCollegeMappingDTO setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public UserRoleCollegeMappingDTO setCollegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }
}

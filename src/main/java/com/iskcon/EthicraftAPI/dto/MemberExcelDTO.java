package com.iskcon.EthicraftAPI.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MemberExcelDTO {

    private String       firstName;
    private String       lastName;
    private Long         mobileNumber;
    private Long         whatsappNumber;
    private String       email;
    private String       collegeName;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<String> hobbies;
    private String       city;

    public String getFirstName() {
        return firstName;
    }

    public MemberExcelDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public MemberExcelDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public MemberExcelDTO setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public Long getWhatsappNumber() {
        return whatsappNumber;
    }

    public MemberExcelDTO setWhatsappNumber(Long whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public MemberExcelDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public MemberExcelDTO setCollegeName(String collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public MemberExcelDTO setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
        return this;
    }

    public String getCity() {
        return city;
    }

    public MemberExcelDTO setCity(String city) {
        this.city = city;
        return this;
    }
}

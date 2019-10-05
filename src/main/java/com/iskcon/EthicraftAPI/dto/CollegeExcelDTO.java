package com.iskcon.EthicraftAPI.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CollegeExcelDTO {

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<String>       collegeName;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<String>       collegeAbbreviation;
    private String       universityName;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<String> address;
    private String       city;
    private String       state;
    private String       comments;
    private String       faculty;
    private String       referenceName;
    private String       referenceDesignation;
    private String       referenceContact;

    public String getCollegeName() {
        return String.join(",",collegeName);
    }

    public CollegeExcelDTO setCollegeName(List<String> collegeName) {
        this.collegeName = collegeName;
        return this;
    }

    public CollegeExcelDTO setAddress(List<String> address) {
        this.address = address;
        return this;
    }

    public String getCollegeAbbreviation() {
        return String.join(",",collegeAbbreviation);
    }

    public CollegeExcelDTO setCollegeAbbreviation(List<String> collegeAbbreviation) {
        this.collegeAbbreviation = collegeAbbreviation;
        return this;
    }

    public String getUniversityName() {
        return universityName;
    }

    public CollegeExcelDTO setUniversityName(String universityName) {
        this.universityName = universityName;
        return this;
    }

    public String getAddress() {
        return String.join(",",address);
    }

    public String getCity() {
        return city;
    }

    public CollegeExcelDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public CollegeExcelDTO setState(String state) {
        this.state = state;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public CollegeExcelDTO setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public String getFaculty() {
        return faculty;
    }

    public CollegeExcelDTO setFaculty(String faculty) {
        this.faculty = faculty;
        return this;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public CollegeExcelDTO setReferenceName(String referenceName) {
        this.referenceName = referenceName;
        return this;
    }

    public String getReferenceDesignation() {
        return referenceDesignation;
    }

    public CollegeExcelDTO setReferenceDesignation(String referenceDesignation) {
        this.referenceDesignation = referenceDesignation;
        return this;
    }

    public String getReferenceContact() {
        return referenceContact;
    }

    public CollegeExcelDTO setReferenceContact(String referenceContact) {
        this.referenceContact = referenceContact;
        return this;
    }
}

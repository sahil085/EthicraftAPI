package com.iskcon.EthicraftAPI.dto;

import java.util.List;

public class CollegeDTO {

    private Long id;

    private String collegeName;
    private String collegeAbbreviation;
    private String universityName;
    private String address;
    private String city;
    private String state;
    private String comments;
    private String faculty;
    private List<CollegeReferenceDTO> referenceList;
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeAbbreviation() {
        return collegeAbbreviation;
    }

    public void setCollegeAbbreviation(String collegeAbbreviation) {
        this.collegeAbbreviation = collegeAbbreviation;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<CollegeReferenceDTO> getReferenceList() {
        return referenceList;
    }

    public CollegeDTO setReferenceList(List<CollegeReferenceDTO> referenceList) {
        this.referenceList = referenceList;
        return this;
    }
}

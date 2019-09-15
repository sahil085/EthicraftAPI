package com.iskcon.EthicraftAPI.co;

import javax.validation.constraints.NotNull;

import java.util.List;

public class CollegeCO {


    @NotNull
    private String                   collegeName;
    @NotNull
    private String                   collegeAbbreviation;
    @NotNull
    private String                   universityName;
    @NotNull
    private String                   address;
    @NotNull
    private String                   city;
    @NotNull
    private String                   state;
    private String                   comments;
    @NotNull
    private String                   faculty;
    @NotNull
    private List<CollegeReferenceCO> referenceList;

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

    public List<CollegeReferenceCO> getReferenceList() {
        return referenceList;
    }

    public CollegeCO setReferenceList(List<CollegeReferenceCO> referenceList) {
        this.referenceList = referenceList;
        return this;
    }

}

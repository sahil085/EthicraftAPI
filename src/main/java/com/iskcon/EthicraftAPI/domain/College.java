package com.iskcon.EthicraftAPI.domain;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

@Entity
@Embeddable
@Table(name = "college")
public class College extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String                 collegeName;
    @Column(nullable = false)
    private String                 collegeAbbreviation;
    @Column(nullable = false)
    private String                 universityName;
    @Column(nullable = false)
    private String                 address;
    @Column(nullable = false)
    private String                 city;
    @Column(nullable = false)
    private String                 state;
    private String                 comments;
    private String                 faculty;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CollegeReference> referenceList;
    private Boolean                isActive = true;

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

    public List<CollegeReference> getReferenceList() {
        return referenceList;
    }

    public College setReferenceList(List<CollegeReference> referenceList) {
        this.referenceList = referenceList;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "College{" +
                "id=" + id +
                ", collegeName='" + collegeName + '\'' +
                ", collegeAbbreviation='" + collegeAbbreviation + '\'' +
                ", universityName='" + universityName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", comments='" + comments + '\'' +
                ", faculty='" + faculty + '\'' +
                ", referenceList=" + referenceList +
                ", isActive=" + isActive +
                '}';
    }
}

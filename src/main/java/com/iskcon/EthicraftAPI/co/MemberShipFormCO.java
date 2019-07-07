package com.iskcon.EthicraftAPI.co;


import com.iskcon.EthicraftAPI.domain.Address;
import com.iskcon.EthicraftAPI.domain.College;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class MemberShipFormCO {


    @NotNull
    private String firstName;
    private String middleName;
    @NotNull
    private String lastName;
    @NotNull
    private String gender;
    @NotNull
    private Long mobileNumber;
    @NotNull
    private String whatsappNumber ;
    @NotNull
    private String email;
    @NotNull
    private College college;
    @NotNull
    private String courseName;
    @NotNull
    private String batch;
    @NotNull
    private AddressCO permanentAddress;
    @NotNull
    private AddressCO presentAddress;
    private String achievements;
    private String hobbies;
    private String skills;
    private String inspirationSource;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public AddressCO getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(AddressCO permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public AddressCO getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(AddressCO presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getInspirationSource() {
        return inspirationSource;
    }

    public void setInspirationSource(String inspirationSource) {
        this.inspirationSource = inspirationSource;
    }
}

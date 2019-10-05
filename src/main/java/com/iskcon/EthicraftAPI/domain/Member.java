package com.iskcon.EthicraftAPI.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    private String middleName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false,unique = true)
    private Long mobileNumber;
    @Column(unique = true)
    private Long whatsappNumber ;
    @Column(nullable = false,unique = true)
    private String email;
    @OneToOne
    private College college;
    private String unRegisteredCollege;
    @Column(nullable = false)
    private String courseName;
    @Column(nullable = false)
    private String batch;
    @OneToOne
    @Cascade(CascadeType.ALL)
    private Address permanentAddress;
    @OneToOne
    @Cascade(CascadeType.ALL)
    private Address presentAddress;
    private String achievements;
    private String hobbies;
    private String skills;
    private String inspirationSource;
    @Column(nullable = false)
    private String profilePic;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String membershipId;

    private Boolean isMemberApproved = false;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(Long whatsappNumber) {
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Boolean getMemberApproved() {
        return isMemberApproved;
    }

    public void setMemberApproved(Boolean memberApproved) {
        isMemberApproved = memberApproved;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Address getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(Address presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public Member setMembershipId(String membershipId) {
        this.membershipId = membershipId;
        return this;
    }

    public Member setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUnRegisteredCollege() {
        return unRegisteredCollege;
    }

    public Member setUnRegisteredCollege(String unRegisteredCollege) {
        this.unRegisteredCollege = unRegisteredCollege;
        return this;
    }


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", whatsappNumber=" + whatsappNumber +
                ", email='" + email + '\'' +
                ", college=" + college +
                ", unRegisteredCollege='" + unRegisteredCollege + '\'' +
                ", courseName='" + courseName + '\'' +
                ", batch='" + batch + '\'' +
                ", permanentAddress=" + permanentAddress +
                ", presentAddress=" + presentAddress +
                ", achievements='" + achievements + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", skills='" + skills + '\'' +
                ", inspirationSource='" + inspirationSource + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", password='" + password + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", isMemberApproved=" + isMemberApproved +
                '}';
    }
}

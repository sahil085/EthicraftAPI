package com.iskcon.EthicraftAPI.co;

import javax.validation.constraints.NotNull;

public class QuickMembershipCO {

    @NotNull(message = "First Name cannot be null")
    private String firstName;
    @NotNull(message = "Last Name cannot be null")
    private String lastName;
    @NotNull(message = "Gender cannot be null")
    private String gender;
    @NotNull(message = "Mobile number cannot be null")
    private Long   mobileNumber;
    @NotNull(message = "Email id cannot be null")
    private String email;
    private Long   college;
    private String unRegisteredCollege;
    @NotNull(message = "Address cannot be null")
    private String address;
    @NotNull(message = "State cannot be null")
    private String state;
    @NotNull(message = "City cannot be null")
    private String city;

    public String getFirstName() {
        return firstName;
    }

    public QuickMembershipCO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public QuickMembershipCO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public QuickMembershipCO setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public QuickMembershipCO setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public QuickMembershipCO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getCollege() {
        return college;
    }

    public QuickMembershipCO setCollege(Long college) {
        this.college = college;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public QuickMembershipCO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getUnRegisteredCollege() {
        return unRegisteredCollege;
    }

    public QuickMembershipCO setUnRegisteredCollege(String unRegisteredCollege) {
        this.unRegisteredCollege = unRegisteredCollege;
        return this;
    }

    public String getState() {
        return state;
    }

    public QuickMembershipCO setState(String state) {
        this.state = state;
        return this;
    }

    public String getCity() {
        return city;
    }

    public QuickMembershipCO setCity(String city) {
        this.city = city;
        return this;
    }

    public AddressCO getAddressObject() {
        AddressCO addressCO = new AddressCO();
        addressCO.setAddress(this.address);
        addressCO.setCity(this.city);
        addressCO.setState(this.state);
        return addressCO;
    }
}

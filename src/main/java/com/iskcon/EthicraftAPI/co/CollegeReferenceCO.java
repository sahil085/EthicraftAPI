package com.iskcon.EthicraftAPI.co;

public class CollegeReferenceCO {

    private String name;
    private Long   contact;
    private String   designation;

    public String getName() {
        return name;
    }

    public CollegeReferenceCO setName(String name) {
        this.name = name;
        return this;
    }

    public Long getContact() {
        return contact;
    }

    public CollegeReferenceCO setContact(Long contact) {
        this.contact = contact;
        return this;
    }

    public String getDesignation() {
        return designation;
    }

    public CollegeReferenceCO setDesignation(String designation) {
        this.designation = designation;
        return this;
    }
}

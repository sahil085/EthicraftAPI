package com.iskcon.EthicraftAPI.dto;

public class CollegeReferenceDTO {

    private String name;
    private String designation;
    private Long contact;

    public String getName() {
        return name;
    }

    public CollegeReferenceDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDesignation() {
        return designation;
    }

    public CollegeReferenceDTO setDesignation(String designation) {
        this.designation = designation;
        return this;
    }

    public Long getContact() {
        return contact;
    }

    public CollegeReferenceDTO setContact(Long contact) {
        this.contact = contact;
        return this;
    }
}

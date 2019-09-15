package com.iskcon.EthicraftAPI.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "college_reference")
public class CollegeReference {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long   contact;
    @Column(nullable = false)
    private String designation;

    public Long getId() {
        return id;
    }

    public CollegeReference setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CollegeReference setName(String name) {
        this.name = name;
        return this;
    }

    public Long getContact() {
        return contact;
    }

    public CollegeReference setContact(Long contact) {
        this.contact = contact;
        return this;
    }

    public String getDesignation() {
        return designation;
    }

    public CollegeReference setDesignation(String designation) {
        this.designation = designation;
        return this;
    }
}

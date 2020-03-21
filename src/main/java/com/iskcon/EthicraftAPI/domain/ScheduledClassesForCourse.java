package com.iskcon.EthicraftAPI.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.Date;

import lombok.Data;

@Entity
@Data
@Table(name = "scheduled_classes_for_course")
public class ScheduledClassesForCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long collegeId;
    private String classStartTime;
    private String classEndTime;
    private String classDescription;
    private Date classDate;
    /* active = false, when class is cancelled or */
    private Boolean active = true;

    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;
}

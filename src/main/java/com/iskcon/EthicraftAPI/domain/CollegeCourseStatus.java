package com.iskcon.EthicraftAPI.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.iskcon.EthicraftAPI.constants.CollegeCourseStatusConstant;
import lombok.Data;

@Entity
@Data
@Table(name = "college_course_status")
public class CollegeCourseStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long collegeId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CollegeCourseStatusConstant collegeCourseStatus = CollegeCourseStatusConstant.DATA_PENDING;

}

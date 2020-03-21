package com.iskcon.EthicraftAPI.domain;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

import com.iskcon.EthicraftAPI.constants.CourseTimeType;
import lombok.Data;

@Entity
@Data
@Table(name = "course")
public class Course extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String abbreviation;

    @Column(nullable = false)
    private String thumbnailUrl = "https://w0.pngwave.com/png/705/643/training-computer-icons-educational-technology-course-training-png-clip-art-thumbnail.png";

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseTimeType courseTimeType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ScheduledClassesForCourse> scheduledClassesForCourseList;

    @ElementCollection
    private List<Long> collegeIds;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CollegeCourseStatus> collegeCourseStatusList;

    @Column(nullable = false)
    private Boolean isApproved = true;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean active = true;

}

package com.iskcon.EthicraftAPI.dto;

import java.util.Date;
import java.util.List;

import com.iskcon.EthicraftAPI.constants.CourseTimeType;
import com.iskcon.EthicraftAPI.domain.ScheduledClassesForCourse;
import lombok.Data;

@Data
public class CourseDTO {

    private Long id;

    private String name;

    private String description;

    private String thumbnailUrl;

    private Date startDate;

    private Date endDate;

    private CourseTimeType courseTimeType;

    private List<ScheduledClassesForCourse> scheduledClassesForCourseList;

    private Boolean isApproved;

    private Boolean active;
}

package com.iskcon.EthicraftAPI.co;

import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iskcon.EthicraftAPI.constants.CourseTimeType;
import lombok.Data;

@Data
public class CourseCO {

    private Long id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Abbreviation cannot be null")
    private String abbreviation;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Start date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "End date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @NotNull(message = "Course time type cannot be null")
    private CourseTimeType courseTimeType;

    private List<CollegeAndTimeForCourseCO> collegeAndTimeForCourseCOList;

    private List<Long> collegeIds;

    private String startTime;

    private String endTime;


}

package com.iskcon.EthicraftAPI.co;

import javax.validation.constraints.NotNull;

import java.util.List;

import com.iskcon.EthicraftAPI.constants.CollegeCourseStatusConstant;
import lombok.Data;

@Data
public class CollegeCourseStatusCO {

    @NotNull(message = "Course id cannot be null")
    Long                        courseId;
    @NotNull(message = "College id cannot be null")
    List<Long>                  collegeIdList;
    @NotNull(message = "Status cannot be null")
    CollegeCourseStatusConstant collegeCourseStatusConstant;

}

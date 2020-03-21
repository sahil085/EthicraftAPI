package com.iskcon.EthicraftAPI.constants;

public enum  CollegeCourseStatusConstant {

    /*Data pending, course started and course */
    DATA_PENDING ("DATA_PENDING"),
    COURSE_STARTED ("COURSE_STARTED"),
    COURSE_COMPLETED ("COURSE_COMPLETED"),
    COURSE_CANCELED_BEFORE_COMPLETION ("COURSE_CANCELED_BEFORE_COMPLETION"),
    COURSE_DISAPPROVED ("COURSE_DISAPPROVED");

    String name;

    CollegeCourseStatusConstant(String name){
        this.name = name;
    }
}

package com.iskcon.EthicraftAPI.constants;

public enum CourseTimeType {


    Weekly("Weekly"),
    Monthly("Monthly"),
    BiWeekly("BiWeekly");

    String name;

    CourseTimeType(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}

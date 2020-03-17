package com.iskcon.EthicraftAPI.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iskcon.EthicraftAPI.co.CollegeCourseStatusCO;
import com.iskcon.EthicraftAPI.co.CourseCO;
import com.iskcon.EthicraftAPI.constants.CollegeCourseStatusConstant;
import com.iskcon.EthicraftAPI.domain.CollegeCourseStatus;
import com.iskcon.EthicraftAPI.domain.Course;
import com.iskcon.EthicraftAPI.domain.ScheduledClassesForCourse;
import com.iskcon.EthicraftAPI.dto.ResponseDTO;
import com.iskcon.EthicraftAPI.repository.CollegeCourseStatusRepo;
import com.iskcon.EthicraftAPI.repository.CourseRepository;
import com.iskcon.EthicraftAPI.repository.ScheduledClassesForCourseRepo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CourseService {

    private ModelMapper modelMapper;

    @Autowired
    private CollegeService                collegeService;
    @Autowired
    private CourseRepository              courseRepository;
    @Autowired
    private CollegeCourseStatusRepo       collegeCourseStatusRepo;
    @Autowired
    private ScheduledClassesForCourseRepo scheduledClassesForCourseRepo;
    @Autowired
    private MemberService                 memberService;


    CourseService() {
        this.modelMapper = new ModelMapper();
    }

    @Transactional
    public ResponseDTO create(MultipartFile file, CourseCO courseCO) {
        try {
            String successMessage = "Course created successfully";
            Course course = modelMapper.map(courseCO, Course.class);
            if (Objects.nonNull(courseCO.getCollegeIds()) && ThreadRequestService.fetchCurrentRole().isAdmin()) {
                /* UtilService.uploadFile(file); */
                course = courseRepository.save(course);
                course.setCollegeCourseStatusList(setCollegeCourseStatus(course, CollegeCourseStatusConstant.DATA_PENDING));
                /* Class will not scheduled from here because timings could be different of respective college */
                /* TODO Publish email and SMS notification to all CA of respective college to enter timings */
            } else if (ThreadRequestService.fetchCurrentRole().isCA()) {
                String email = ThreadRequestService.fetchCurrentUser().getEmail();
                course.setIsApproved(false);
                course.setCollegeIds(Collections.singletonList(memberService.findMemberCollege(email).getId()));
                /* UtilService.uploadFile(file); */
                course.setScheduledClassesForCourseList(scheduleCourseClasses(courseCO, course));
                course.setCollegeCourseStatusList(setCollegeCourseStatus(course, CollegeCourseStatusConstant.COURSE_STARTED));
                courseRepository.save(course);
                /* TODO Publish email and SMS notification to ADMIN for approval */
                successMessage = "Course created successfully. Request sent for approval";
            }
            return ResponseDTO.sendSuccessmessage(successMessage);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in creating course :: " + e.getMessage());

            throw new RuntimeException("Error in creating course :: " + e.getMessage());
        }

    }

    private List<CollegeCourseStatus> setCollegeCourseStatus(Course course, CollegeCourseStatusConstant collegeCourseStatusConstant) {
        List<CollegeCourseStatus> collegeCourseStatusList = new ArrayList<>();
        course.getCollegeIds().forEach(id -> {
            CollegeCourseStatus collegeCourseStatus = new CollegeCourseStatus();
            collegeCourseStatus.setCollegeId(id);
            collegeCourseStatus.setCollegeCourseStatus(collegeCourseStatusConstant);
            collegeCourseStatusList.add(collegeCourseStatus);
        });
        return collegeCourseStatusList;
    }

    private List<ScheduledClassesForCourse> scheduleCourseClasses(CourseCO courseCO, Course course) {
        log.info("Schedule class start for course :: " + course.getName());
        LocalDate startDate = UtilService.convertDateToLocalDate(course.getStartDate());
        LocalDate endDate = UtilService.convertDateToLocalDate(course.getEndDate());
        LocalDate classDate = startDate;
        long daysToAdd = 0;
        List<ScheduledClassesForCourse> scheduledClassesForCourseList = new ArrayList<>();
        switch (course.getCourseTimeType().getName()) {
            case "Weekly":
                daysToAdd = 7;
                break;
            case "Biweekly":
                daysToAdd = 14;
                break;
            case "Monthly":
                daysToAdd = 30;
                break;
        }
        for (Long id : course.getCollegeIds()) {
            while (classDate.isBefore(endDate) || classDate.isEqual(endDate)) {
                ScheduledClassesForCourse scheduledClassesForCourse = new ScheduledClassesForCourse();
                scheduledClassesForCourse.setCollegeId(id);
                scheduledClassesForCourse.setClassStartTime(courseCO.getStartTime());
                scheduledClassesForCourse.setClassEndTime(courseCO.getEndTime());
                scheduledClassesForCourse.setClassDate(UtilService.convertLocalDateToDate(classDate));
                scheduledClassesForCourse.setCourse(course);
                classDate = classDate.plusDays(daysToAdd);
                scheduledClassesForCourseList.add(scheduledClassesForCourse);
            }
            classDate = startDate;
        }
        log.info("Schedule class end for course :: " + course.getName());
        return scheduledClassesForCourseList;
    }

    public ResponseDTO approveCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        course.setIsApproved(true);
        courseRepository.save(course);
        return ResponseDTO.sendSuccessmessage("Course approved successfully");
    }

    public ResponseDTO insertClassTimings(CourseCO courseCO) {
        Course course = courseRepository.findById(courseCO.getId()).get();
        long collegeId = memberService.findMemberCollege(ThreadRequestService.fetchCurrentUser().getEmail()).getId();
        // Temporary Resting the college Ids in course domain to use same schedule and college course status method //
        courseCO.setCollegeIds(course.getCollegeIds());
        course.setCollegeIds(Collections.singletonList(collegeId));
        if(course.getCollegeCourseStatusList().stream().filter(collegeCourseStatus -> collegeCourseStatus.getCollegeId() == collegeId).findFirst().get().getCollegeCourseStatus().equals(CollegeCourseStatusConstant.DATA_PENDING)){
            if(course.getScheduledClassesForCourseList() == null){
                course.setScheduledClassesForCourseList(scheduleCourseClasses(courseCO,course));
            }else{
                course.getScheduledClassesForCourseList().addAll(scheduleCourseClasses(courseCO,course));
            }
            course.setCollegeCourseStatusList(course.getCollegeCourseStatusList().stream().peek(collegeCourseStatus -> {
                if(collegeCourseStatus.getCollegeId() == collegeId){
                    collegeCourseStatus.setCollegeCourseStatus(CollegeCourseStatusConstant.COURSE_STARTED);
                }
            }).collect(Collectors.toList()));
            // Reset back to original data again
            course.setCollegeIds(courseCO.getCollegeIds());
            courseRepository.saveAndFlush(course);
            return ResponseDTO.sendSuccessmessage("Class scheduled successfully");
        }else{
            return ResponseDTO.sendSuccessmessage("Classes already scheduled for this course");
        }

    }

    public ResponseDTO updateCourse(CourseCO courseCO){
        Course course = courseRepository.getOne(courseCO.getId());
        course.setName(courseCO.getName());
        course.setAbbreviation(courseCO.getAbbreviation());
        course.setDescription(courseCO.getDescription());
        courseRepository.saveAndFlush(course);
        return ResponseDTO.sendSuccessmessage("Course details updated successfully.");
    }

    public ResponseDTO updateCollegeCourseStatus(CollegeCourseStatusCO collegeCourseStatusCO){
        Course course = courseRepository.findById(collegeCourseStatusCO.getCourseId()).get();
        course.setCollegeCourseStatusList(course.getCollegeCourseStatusList().stream().peek(collegeCourseStatus -> {
            if(collegeCourseStatusCO.getCollegeIdList().contains(collegeCourseStatus.getCollegeId())){
                collegeCourseStatus.setCollegeCourseStatus(collegeCourseStatusCO.getCollegeCourseStatusConstant());
            }
        }).collect(Collectors.toList()));
        if(!CollegeCourseStatusConstant.DATA_PENDING.equals(collegeCourseStatusCO.getCollegeCourseStatusConstant()) &&
           !CollegeCourseStatusConstant.COURSE_STARTED.equals(collegeCourseStatusCO.getCollegeCourseStatusConstant())){
//            updateCollegeCourseStatus(course, collegeCourseStatusCO.getCollegeIdList(), )
        }
        courseRepository.saveAndFlush(course);
        return ResponseDTO.sendSuccessmessage("College course status updated successfully.");
    }

    /*private void updateScheduledClassStatus(Coll collegeId,Course course){
        course.getScheduledClassesForCourseList().stream().f
    }*/

}

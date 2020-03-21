package com.iskcon.EthicraftAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iskcon.EthicraftAPI.domain.ScheduledClassesForCourse;

@Repository
public interface ScheduledClassesForCourseRepo extends JpaRepository<ScheduledClassesForCourse, Long> {
}

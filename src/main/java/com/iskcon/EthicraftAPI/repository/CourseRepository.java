package com.iskcon.EthicraftAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iskcon.EthicraftAPI.domain.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}

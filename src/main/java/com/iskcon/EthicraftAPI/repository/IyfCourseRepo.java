package com.iskcon.EthicraftAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iskcon.EthicraftAPI.domain.IyfCourse;

@Repository
public interface IyfCourseRepo extends JpaRepository<IyfCourse, Long> {


}

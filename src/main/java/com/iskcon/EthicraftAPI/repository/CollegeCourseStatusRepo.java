package com.iskcon.EthicraftAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iskcon.EthicraftAPI.domain.CollegeCourseStatus;

@Repository
public interface CollegeCourseStatusRepo extends JpaRepository<CollegeCourseStatus, Long> {
}

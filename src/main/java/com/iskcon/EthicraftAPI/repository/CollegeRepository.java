package com.iskcon.EthicraftAPI.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iskcon.EthicraftAPI.domain.College;

@Repository
public interface CollegeRepository extends JpaRepository<College,Long> {

    Integer countByCollegeName(String collegeName);
    List<College> findAllByIsActive(Boolean isActive);
    List<College> findAllByIdIn(List<Long> collegeIds);

}

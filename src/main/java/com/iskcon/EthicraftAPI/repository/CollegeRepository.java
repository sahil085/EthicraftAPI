package com.iskcon.EthicraftAPI.repository;


import com.iskcon.EthicraftAPI.domain.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College,Long> {

    Integer countByCollegeName(String collegeName);
}

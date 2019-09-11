package com.iskcon.EthicraftAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iskcon.EthicraftAPI.domain.UserRoleCollegeMapping;

@Repository
public interface UserRoleCollegeRepo extends JpaRepository<UserRoleCollegeMapping,Long> {

}

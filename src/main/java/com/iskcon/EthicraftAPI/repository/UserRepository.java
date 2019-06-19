package com.iskcon.EthicraftAPI.repository;

import com.iskcon.EthicraftAPI.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

      User findByEmail(String username);


    @Override
    User getOne(Long Long);
}

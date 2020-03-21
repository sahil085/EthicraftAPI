package com.iskcon.EthicraftAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iskcon.EthicraftAPI.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

      User findByEmail(String username);

    @Query(value = "select email from user ", nativeQuery = true)
    List<String> findAllEmail();

    @Query(value = "select User_id as userId, roles_id as roleId from user_role where User_id=:userId", nativeQuery = true)
    List<UserRole> findUserRoleByUserId(Long userId);

    @Override
    User getOne(Long Long);

     interface UserRole {
        Long getUserId();
        Long getRoleId();
    }
}

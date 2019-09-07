package com.iskcon.EthicraftAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iskcon.EthicraftAPI.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRole(String role);

    List<Role> findByRoleIn(List<String> roleList);
}

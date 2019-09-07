package com.iskcon.EthicraftAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iskcon.EthicraftAPI.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Integer countByEmail(String email);

    @Query(value = "select email from Member", nativeQuery = true)
    List<String> findAllEmail();

    Member findByEmail(String email);
}

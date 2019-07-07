package com.iskcon.EthicraftAPI.repository;

import com.iskcon.EthicraftAPI.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
}

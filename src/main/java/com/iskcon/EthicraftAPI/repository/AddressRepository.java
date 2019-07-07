package com.iskcon.EthicraftAPI.repository;

import com.iskcon.EthicraftAPI.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}

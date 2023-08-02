package com.example.mapping.repository;

import com.example.mapping.one_to_one_unidirectional.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
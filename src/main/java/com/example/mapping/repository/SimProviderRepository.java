package com.example.mapping.repository;

import com.example.mapping.entity.SimProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimProviderRepository extends JpaRepository<SimProvider,Integer> {

}

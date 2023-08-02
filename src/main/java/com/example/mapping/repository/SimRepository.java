package com.example.mapping.repository;

import com.example.mapping.entity.Sim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimRepository extends JpaRepository<Sim,Integer> {

}

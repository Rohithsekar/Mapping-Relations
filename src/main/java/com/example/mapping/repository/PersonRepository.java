package com.example.mapping.repository;

import com.example.mapping.one_to_many_unidirectional.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
}

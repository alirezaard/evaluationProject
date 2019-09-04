package com.gamelectronics.evaluateProject.dao;

import com.gamelectronics.evaluateProject.domain.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findFirstByUniqueId(String uuid);
    void deleteAllByUniqueId(String uniqueId);
    Page<Person> findAll(Pageable page);
    boolean existsByUniqueId(String uniqueId);
    boolean existsByFirstNameAndLastNameAndMobile(String fName,String lName,String mobile);
    void deleteAllByUniqueIdIn(List<String> fakeUniqueId);
}
package com.gamelectronics.evaluateProject.dao;

import com.gamelectronics.evaluateProject.domain.entities.Units;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitsRepository extends CrudRepository<Units, Long> {
    Optional<Units> findFirstByUniqueId(String uuid);
    void deleteAllByUniqueId(String uniqueId);
    boolean existsByUniqueId(String uuid);
    boolean existsByBuilding_UniqueIdAndLocation(String uuid,String location);
    Page<Units> findAll(Pageable pageable);
    void deleteAllByUniqueIdIn(List<String> fakeUniqueId);
}
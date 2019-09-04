package com.gamelectronics.evaluateProject.dao;

import com.gamelectronics.evaluateProject.domain.entities.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends CrudRepository<Building, Long> {
    Optional<Building> findFirstByUniqueId(String uuid);
    void deleteAllByUniqueId(String uniqueId);
    boolean existsByUniqueId(String uniqueId);
    Page<Building> findAll(Pageable page);
    boolean existsByAccount_AccountNumberAndManager_Mobile(String accountNumber, String managerMobile);
    void deleteAllByUniqueIdIn(List<String> fakeUniqueId);
}
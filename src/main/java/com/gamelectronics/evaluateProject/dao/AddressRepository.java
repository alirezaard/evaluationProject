package com.gamelectronics.evaluateProject.dao;

import com.gamelectronics.evaluateProject.domain.entities.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    Optional<Address> findFirstByUniqueId(String uuid);
    void deleteAllByUniqueId(String uniqueId);
    boolean existsByUniqueId(String uuid);
    boolean existsByStateAndCityAndPostalCode(String state, String city, String postalCode);
    Page<Address> findAll(Pageable page);
    void deleteAllByUniqueIdIn(List<String> fakeUniqueId);
}
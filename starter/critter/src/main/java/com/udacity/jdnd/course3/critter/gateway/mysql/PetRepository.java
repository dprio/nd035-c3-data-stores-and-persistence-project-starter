package com.udacity.jdnd.course3.critter.gateway.mysql;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.domain.user.Customer;

import java.util.List;
import java.util.Optional;

public interface PetRepository {

    Long save(Pet pet);

    List<Pet> findAll();

    Optional<Pet> findById(Long petId);

    List<Pet> findByOwner(Customer customer);

}

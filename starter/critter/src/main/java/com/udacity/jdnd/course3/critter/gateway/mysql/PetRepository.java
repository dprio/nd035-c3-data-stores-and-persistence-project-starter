package com.udacity.jdnd.course3.critter.gateway.mysql;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.domain.user.Customer;

import java.util.List;

public interface PetRepository {

    Long save(Pet pet);

    List<Pet> findAll();

    Pet findById(Long petId);

    List<Pet> findByOwner(Customer customer);

}

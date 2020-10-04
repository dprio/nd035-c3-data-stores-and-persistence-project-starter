package com.udacity.jdnd.course3.critter.gateway.mysql;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.domain.user.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

     Long save(Customer customer);

     List<Customer> findAll();

     Customer findByPet(Pet petID);

     Optional<Customer> findById(Long customerId);

}

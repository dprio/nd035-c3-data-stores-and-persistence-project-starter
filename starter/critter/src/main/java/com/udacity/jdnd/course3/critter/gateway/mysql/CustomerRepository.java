package com.udacity.jdnd.course3.critter.gateway.mysql;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.domain.user.Customer;

import java.util.List;

public interface CustomerRepository {

     Long save(Customer customer);

     List<Customer> findAll();

     Customer findByPet(Pet petID);

}

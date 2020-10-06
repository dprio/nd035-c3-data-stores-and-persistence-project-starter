package com.udacity.jdnd.course3.critter.service.customer;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.domain.user.Customer;
import com.udacity.jdnd.course3.critter.gateway.mysql.CustomerRepository;
import com.udacity.jdnd.course3.critter.gateway.mysql.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CustomerFindByPet {

    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    public Customer execute(final Long petID){
        final Pet pet = petRepository.findById(petID)
                .orElseThrow(EntityNotFoundException::new);

        return customerRepository.findByPet(pet);
    }

}

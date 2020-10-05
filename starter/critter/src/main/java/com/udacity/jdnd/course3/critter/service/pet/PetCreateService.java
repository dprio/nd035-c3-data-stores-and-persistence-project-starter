package com.udacity.jdnd.course3.critter.service.pet;

import com.udacity.jdnd.course3.critter.domain.pet.Pet;
import com.udacity.jdnd.course3.critter.domain.user.Customer;
import com.udacity.jdnd.course3.critter.gateway.mysql.CustomerRepository;
import com.udacity.jdnd.course3.critter.gateway.mysql.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PetCreateService {

    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public Pet execute (final Pet pet){
        final Customer owner = customerRepository.findById(pet.getOwner().getId())
                .orElseThrow(EntityNotFoundException::new);
        pet.setOwner(owner);

        return petRepository.save(pet);
    }
}
